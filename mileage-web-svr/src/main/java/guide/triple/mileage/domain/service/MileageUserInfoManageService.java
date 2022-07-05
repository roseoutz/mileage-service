package guide.triple.mileage.domain.service;

import guide.triple.mileage.domain.dto.MileageUserInfoDTO;

/**
 * packageName    : guide.triple.mileage.service
 * fileName       : MileageUserInfoManageService
 * author         : kimdonggyuuuuu
 * date           : 2022/06/30
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/06/30        kimdonggyuuuuu       최초 생성
 */
public interface MileageUserInfoManageService {

    MileageUserInfoDTO get(String userId);

    MileageUserInfoDTO add(MileageUserInfoDTO mileageUserInfoDTO);

    void update(String userId, long point);
}
