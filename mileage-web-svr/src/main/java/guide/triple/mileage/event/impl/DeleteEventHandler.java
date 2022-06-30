package guide.triple.mileage.event.impl;

import guide.triple.mileage.dto.EventsRequestDTO;
import guide.triple.mileage.event.EventHandler;

/**
 * packageName    : guide.triple.mileage.event.impl
 * fileName       : DeleteEventHandler
 * author         : kimdonggyuuuuu
 * date           : 2022/06/30
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/06/30        kimdonggyuuuuu       최초 생성
 */
public class DeleteEventHandler implements EventHandler {


    @Override
    public String getName() {
        return "DELETE";
    }

    @Override
    public void handle(EventsRequestDTO requestDTO) {

    }
}
