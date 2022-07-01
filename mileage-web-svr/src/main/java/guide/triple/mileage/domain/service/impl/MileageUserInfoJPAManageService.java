package guide.triple.mileage.domain.service.impl;

import guide.triple.mileage.domain.dto.MileageUserInfoDTO;
import guide.triple.mileage.domain.entity.MileageUserInfoEntity;
import guide.triple.mileage.domain.repository.MileageUserInfoRepository;
import guide.triple.mileage.domain.service.MileageUserInfoManageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


/**
 * packageName    : guide.triple.mileage.service.impl
 * fileName       : MileageUserInfoJPAManageService
 * author         : kimdonggyuuuuu
 * date           : 2022/06/30
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/06/30        kimdonggyuuuuu       최초 생성
 */

@RequiredArgsConstructor
@Service("mileageUserInfoJPAService")
public class MileageUserInfoJPAManageService implements MileageUserInfoManageService {

    private final MileageUserInfoRepository userInfoRepository;

    @Override
    public MileageUserInfoDTO get(String userId) {
        Optional<MileageUserInfoEntity> optionalMileageUserInfoEntity = userInfoRepository.findById(userId);

        return optionalMileageUserInfoEntity.map(this::toDTO).orElse(null);

    }

    @Override
    public MileageUserInfoDTO add(MileageUserInfoDTO mileageUserInfoDTO) {
        return null;
    }

    @Override
    @Transactional
    public void update(String userId, long point) {
        userInfoRepository.findById(userId)
                .ifPresentOrElse(userInfoEntity -> {
                        long value = userInfoEntity.getPoint() + point;

                        if (value < 0) {
                            value = 0;
                        }

                        userInfoEntity.setPoint(value);
                    }, () -> {
                        userInfoRepository.save(new MileageUserInfoEntity(userId, point));
                    });
    }

    private MileageUserInfoDTO toDTO(MileageUserInfoEntity userInfoEntity) {
        return MileageUserInfoDTO
                .builder()
                .userId(userInfoEntity.getUserId())
                .point(userInfoEntity.getPoint())
                .build();

    }
}
