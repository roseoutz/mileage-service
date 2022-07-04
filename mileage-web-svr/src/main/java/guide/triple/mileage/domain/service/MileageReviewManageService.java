package guide.triple.mileage.domain.service;

import guide.triple.mileage.domain.dto.MileageReviewDTO;

/**
 * packageName    : guide.triple.mileage.service
 * fileName       : MileageReviewManageService
 * author         : kimdonggyuuuuu
 * date           : 2022/06/30
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/06/30        kimdonggyuuuuu       최초 생성
 */
public interface MileageReviewManageService {

    MileageReviewDTO add(MileageReviewDTO mileageReviewDTO);

    MileageReviewDTO update(MileageReviewDTO mileageReviewDTO);

    MileageReviewDTO delete(MileageReviewDTO mileageReviewDTO);
}
