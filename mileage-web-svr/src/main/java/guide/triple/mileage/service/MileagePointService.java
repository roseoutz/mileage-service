package guide.triple.mileage.service;

import guide.triple.mileage.dto.EventsRequestDTO;

/**
 * packageName    : guide.triple.mileage.service
 * fileName       : MileagePointService
 * author         : kimdonggyuuuuu
 * date           : 2022/06/30
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/06/30        kimdonggyuuuuu       최초 생성
 */
public interface MileagePointService {

    void event(EventsRequestDTO requestDTO);
}
