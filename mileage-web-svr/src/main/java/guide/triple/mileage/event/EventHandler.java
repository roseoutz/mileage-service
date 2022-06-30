package guide.triple.mileage.event;

import guide.triple.mileage.dto.EventsRequestDTO;

/**
 * packageName    : guide.triple.mileage.event
 * fileName       : EventHandler
 * author         : kimdonggyuuuuu
 * date           : 2022/06/30
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/06/30        kimdonggyuuuuu       최초 생성
 */
public interface EventHandler {

    String getName();

    void handle(EventsRequestDTO requestDTO);
}
