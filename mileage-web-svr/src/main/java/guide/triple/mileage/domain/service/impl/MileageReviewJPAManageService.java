package guide.triple.mileage.domain.service.impl;

import guide.triple.mileage.domain.dto.MileageReviewDTO;
import guide.triple.mileage.domain.entity.MileageReviewEntity;
import guide.triple.mileage.domain.entity.composite.MileageId;
import guide.triple.mileage.domain.repository.MileageReviewRepository;
import guide.triple.mileage.domain.service.MileageReviewManageService;
import guide.triple.mileage.domain.service.MileageUserInfoManageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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

    @Transactional
    @Override
    public MileageReviewDTO add(MileageReviewDTO mileageReviewDTO) {

        MileageReviewDTO.MileageReviewDTOBuilder builder = mileageReviewDTO.toBuilder();

        if (isFirstReview(mileageReviewDTO.getPlaceId())) {
            builder.hasBonus(true);
        }

        MileageReviewEntity savedEntity = mileageReviewRepository.saveAndFlush(toEntity(builder.build()));

        addPoint(
                mileageReviewDTO.getUserId(),
                getPoint(mileageReviewDTO.isHasText()),
                getPoint(mileageReviewDTO.isHasImage()),
                getPoint(mileageReviewDTO.isHasBonus())
        );

        return toDTO(savedEntity);
    }

    @Transactional
    @Override
    public MileageReviewDTO update(MileageReviewDTO mileageReviewDTO) {
        Optional<MileageReviewEntity> optionalMileageReviewEntity = mileageReviewRepository.findById(new MileageId(mileageReviewDTO.getUserId(), mileageReviewDTO.getPlaceId()));

        if (optionalMileageReviewEntity.isPresent()) {
            MileageReviewEntity entity = optionalMileageReviewEntity.get();

            addPoint(
                    mileageReviewDTO.getUserId(),
                    getPoint(entity.isHasText(), mileageReviewDTO.isHasText()),
                    getPoint(entity.isHasImage(), mileageReviewDTO.isHasImage()),
                    0

            );

            entity.setHasBonus(mileageReviewDTO.isHasBonus());
            entity.setHasImage(mileageReviewDTO.isHasImage());
            entity.setHasText(mileageReviewDTO.isHasText());


            return toDTO(entity);
        }
        return null;
    }

    @Transactional
    @Override
    public void delete(MileageReviewDTO mileageReviewDTO) {
        MileageId id = new MileageId(mileageReviewDTO.getUserId(), mileageReviewDTO.getPlaceId());
        Optional<MileageReviewEntity> optionalMileageReviewEntity = mileageReviewRepository.findById(id);

        if (optionalMileageReviewEntity.isPresent()) {
            MileageReviewEntity entity = optionalMileageReviewEntity.get();

            addPoint(
                    mileageReviewDTO.getUserId(),
                    getPoint(entity.isHasText()),
                    getPoint(entity.isHasImage()),
                    getPoint(entity.isHasBonus())

            );
        }

        mileageReviewRepository.deleteById(id);
    }

    private boolean isFirstReview(String placeId) {
        return mileageReviewRepository.existsByPlaceId(placeId);
    }

    private long getPoint(boolean oldValue, boolean newValue) {
        if (oldValue && !newValue) {
            return -1;
        }

        if (oldValue == newValue) {
            return 0;
        }

        if (!oldValue && newValue) {
            return 1;
        }

        return 0;
    }

    private long getPoint(boolean value) {
        if (value) {
            return -1;
        }
        return 0;
    }

    private void addPoint(String userId, long text, long image, long bouns) {
        userInfoService.update(userId, text + image + bouns);
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
