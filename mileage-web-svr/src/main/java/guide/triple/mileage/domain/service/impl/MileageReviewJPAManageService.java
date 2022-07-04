package guide.triple.mileage.domain.service.impl;

import guide.triple.mileage.common.constant.ErrorCode;
import guide.triple.mileage.common.exception.MileageException;
import guide.triple.mileage.domain.dto.MileageConfigDTO;
import guide.triple.mileage.domain.dto.MileageReviewDTO;
import guide.triple.mileage.domain.entity.MileageReviewEntity;
import guide.triple.mileage.domain.entity.composite.MileageId;
import guide.triple.mileage.domain.repository.MileageReviewRepository;
import guide.triple.mileage.domain.service.MileageConfigManageService;
import guide.triple.mileage.domain.service.MileageReviewManageService;
import guide.triple.mileage.domain.service.MileageUserInfoManageService;
import guide.triple.mileage.message.KafkaProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * packageName    : guide.triple.mileage.service.impl
 * fileName       : MileageReviewJPAManageService
 * author         : kimdonggyuuuuu
 * date           : 2022/06/30
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/06/30        kimdonggyuuuuu       최초 생성
 */
@RequiredArgsConstructor
@Service("mileageReviewJPAService")
public class MileageReviewJPAManageService implements MileageReviewManageService {

    @Resource(name = "userInfoProducer")
    private KafkaProducer<String> kafkaProducer;

    private final MileageReviewRepository mileageReviewRepository;

    private final MileageUserInfoManageService userInfoService;

    private final MileageConfigManageService configManageService;

    private final Map<String, Integer> pointCacheMap = new ConcurrentHashMap<>();

    private long cacheLoadTime = 0;

    private int getPoint(String key) {

        if (pointCacheMap.isEmpty() || System.currentTimeMillis() > cacheLoadTime + (60 * 5 * 1000)) {
            List<MileageConfigDTO> configDTOS = configManageService.getAll("review.point");
            configDTOS.forEach(dto -> pointCacheMap.put(dto.getKey(), Integer.parseInt(dto.getValue())));
            cacheLoadTime = System.currentTimeMillis();
        }

        if (!pointCacheMap.isEmpty() && pointCacheMap.containsKey("review.point."+ key)) {
            return pointCacheMap.get("review.point."+ key);
        }

        return 1;
    }



    @Transactional
    @Override
    public MileageReviewDTO add(MileageReviewDTO mileageReviewDTO) {

        MileageReviewDTO.MileageReviewDTOBuilder builder = mileageReviewDTO.toBuilder();

        boolean isExistReview = mileageReviewRepository.existsById(new MileageId(mileageReviewDTO.getUserId(), mileageReviewDTO.getPlaceId()));

        if (isExistReview) {
            throw new MileageException(ErrorCode.ERROR_REGISTERED_REVIEW_EXIST);
        }

        if (isFirstReview(mileageReviewDTO.getPlaceId())) {
            builder.hasBonus(true);
        }

        MileageReviewDTO dto = builder.build();

        MileageReviewEntity savedEntity = mileageReviewRepository.saveAndFlush(toEntity(dto));

        updatePoint(
                dto.getUserId(),
                dto.isHasText() ? getPoint("text") : 0,
                dto.isHasImage() ? getPoint("image") : 0,
                dto.isHasBonus() ? getPoint("bonus") : 0
        );

        return toDTO(savedEntity);
    }

    @Transactional
    @Override
    public MileageReviewDTO update(MileageReviewDTO mileageReviewDTO) {
        Optional<MileageReviewEntity> optionalMileageReviewEntity = mileageReviewRepository.findById(new MileageId(mileageReviewDTO.getUserId(), mileageReviewDTO.getPlaceId()));

        if (optionalMileageReviewEntity.isPresent()) {
            MileageReviewEntity entity = optionalMileageReviewEntity.get();

            updatePoint(
                    mileageReviewDTO.getUserId(),
                    checkPositive(entity.isHasText(), mileageReviewDTO.isHasText()) * getPoint("text"),
                    checkPositive(entity.isHasImage(), mileageReviewDTO.isHasImage()) * getPoint("image"),
                    0

            );

            entity.setHasImage(mileageReviewDTO.isHasImage());
            entity.setHasText(mileageReviewDTO.isHasText());


            return toDTO(entity);
        }

        throw new MileageException(ErrorCode.ERROR_REVIEW_NOT_EXIST);
    }

    @Transactional
    @Override
    public void delete(MileageReviewDTO mileageReviewDTO) {
        MileageId id = new MileageId(mileageReviewDTO.getUserId(), mileageReviewDTO.getPlaceId());
        Optional<MileageReviewEntity> optionalMileageReviewEntity = mileageReviewRepository.findById(id);

        if (optionalMileageReviewEntity.isPresent()) {
            MileageReviewEntity entity = optionalMileageReviewEntity.get();

            updatePoint(
                    mileageReviewDTO.getUserId(),
                    entity.isHasText() ? -1 * getPoint("text") : 0,
                    entity.isHasImage() ? -1 * getPoint("image") : 0,
                    entity.isHasBonus() ? -1 * getPoint("bonus") : 0

            );
        }

        mileageReviewRepository.deleteById(id);

        mileageReviewRepository.findFirstByPlaceIdOrderByInsertTimeAsc(mileageReviewDTO.getPlaceId())
                .ifPresent(e -> {
                    e.setHasBonus(true);
                    updatePoint(e.getUserId(), 0, 0, getPoint("bonus"));
                });
    }

    private boolean isFirstReview(String placeId) {
        return !mileageReviewRepository.existsByPlaceId(placeId);
    }

    private long checkPositive(boolean oldValue, boolean newValue) {
        if (oldValue && !newValue) {
            return -1 * getPoint("text");
        }

        if (oldValue == newValue) {
            return 0;
        }

        if (!oldValue && newValue) {
            return 1;
        }

        return 0;
    }

    private void updatePoint(String userId, long text, long image, long bonus) {
        userInfoService.update(userId, text + image + bonus);
        syncUserRequest(userId);
    }

    private void syncUserRequest(String userId) {
        kafkaProducer.sendMessage(userId);
    }

    private MileageReviewEntity toEntity(MileageReviewDTO dto) {
        MileageReviewEntity entity = new MileageReviewEntity();
        entity.setUserId(dto.getUserId());
        entity.setPlaceId(dto.getPlaceId());
        entity.setReviewId(dto.getReviewId());
        entity.setHasBonus(dto.isHasBonus());
        entity.setHasImage(dto.isHasImage());
        entity.setHasText(dto.isHasText());

        return entity;
    }

    private MileageReviewDTO toDTO(MileageReviewEntity entity) {
        return MileageReviewDTO
                .builder()
                .userId(entity.getUserId())
                .placeId(entity.getPlaceId())
                .reviewId(entity.getReviewId())
                .hasBonus(entity.isHasBonus())
                .hasImage(entity.isHasImage())
                .hasText(entity.isHasText())
                .build();
    }
}
