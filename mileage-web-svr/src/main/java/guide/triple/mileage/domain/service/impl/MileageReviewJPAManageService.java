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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        boolean isExistReview = mileageReviewRepository.existsById(new MileageId(mileageReviewDTO.getUserId(), mileageReviewDTO.getPlaceId()));

        if (isExistReview) {
            throw new MileageException(ErrorCode.ERROR_REGISTERED_REVIEW_EXIST);
        }

        MileageReviewEntity entity = toEntity(mileageReviewDTO);

        if (isFirstReview(mileageReviewDTO.getPlaceId())) {
            entity.setBonus(true);
        }


        MileageReviewEntity savedEntity = mileageReviewRepository.saveAndFlush(entity);

        MileageReviewDTO dto = toDTO(savedEntity,
                entity.isText() ? getPoint("text") : 0,
                entity.isImage() ? getPoint("image") : 0,
                entity.isBonus() ? getPoint("bonus") : 0
                );

        updatePoint(dto);

        return dto;
    }

    @Transactional
    @Override
    public MileageReviewDTO update(MileageReviewDTO mileageReviewDTO) {
        Optional<MileageReviewEntity> optionalMileageReviewEntity = mileageReviewRepository.findById(new MileageId(mileageReviewDTO.getUserId(), mileageReviewDTO.getPlaceId()));

        if (optionalMileageReviewEntity.isPresent()) {
            MileageReviewEntity entity = optionalMileageReviewEntity.get();


            MileageReviewDTO dto = toDTO(entity,
                    checkPositive(entity.isText(), mileageReviewDTO.isText()) * getPoint("text"),
                    checkPositive(entity.isImage(), mileageReviewDTO.isImage()) * getPoint("image"),
                    0
            );

            entity.setImage(mileageReviewDTO.isImage());
            entity.setText(mileageReviewDTO.isText());

            updatePoint(dto);

            return dto;
        }

        throw new MileageException(ErrorCode.ERROR_REVIEW_NOT_EXIST);
    }

    @Transactional
    @Override
    public MileageReviewDTO delete(MileageReviewDTO mileageReviewDTO) {
        MileageId id = new MileageId(mileageReviewDTO.getUserId(), mileageReviewDTO.getPlaceId());
        Optional<MileageReviewEntity> optionalMileageReviewEntity = mileageReviewRepository.findById(id);

        if (optionalMileageReviewEntity.isPresent()) {
            MileageReviewEntity entity = optionalMileageReviewEntity.get();

            MileageReviewDTO dto = toDTO(entity,
                    entity.isText() ? -1 * getPoint("text") : 0,
                    entity.isImage() ? -1 * getPoint("image") : 0,
                    entity.isBonus() ? -1 * getPoint("bonus") : 0

            );
            updatePoint(dto);

            mileageReviewRepository.deleteById(id);

            mileageReviewRepository.findFirstByPlaceIdOrderByInsertTimeAsc(mileageReviewDTO.getPlaceId())
                    .ifPresent(e -> {
                        e.setBonus(true);
                        MileageReviewDTO secondReviewDTO = toDTO(entity, 0, 0, getPoint("bonus"));
                        updatePoint(secondReviewDTO);
                    });
            return dto;
        }

        throw new MileageException(ErrorCode.ERROR_REVIEW_NOT_EXIST);
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

    private void updatePoint(MileageReviewDTO mileageReviewDTO) {
        long point = mileageReviewDTO.getBonusPoint() + mileageReviewDTO.getImagePoint() + mileageReviewDTO.getTextPoint();
        userInfoService.update(mileageReviewDTO.getUserId(), point);
    }

    private MileageReviewEntity toEntity(MileageReviewDTO dto) {
        MileageReviewEntity entity = new MileageReviewEntity();
        entity.setUserId(dto.getUserId());
        entity.setPlaceId(dto.getPlaceId());
        entity.setReviewId(dto.getReviewId());
        entity.setBonus(dto.isBonus());
        entity.setImage(dto.isImage());
        entity.setText(dto.isText());

        return entity;
    }

    private MileageReviewDTO toDTO(MileageReviewEntity entity) {
        return MileageReviewDTO
                .builder()
                .userId(entity.getUserId())
                .placeId(entity.getPlaceId())
                .reviewId(entity.getReviewId())
                .bonus(entity.isBonus())
                .image(entity.isImage())
                .text(entity.isText())
                .build();
    }

    private MileageReviewDTO toDTO(MileageReviewEntity entity, long text, long image, long bonus) {
        return toDTO(entity).toBuilder()
                .bonusPoint(bonus)
                .imagePoint(image)
                .textPoint(text)
                .build();
    }
}
