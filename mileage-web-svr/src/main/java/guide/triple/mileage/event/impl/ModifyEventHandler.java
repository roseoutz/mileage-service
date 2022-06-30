package guide.triple.mileage.event.impl;

import guide.triple.mileage.dto.EventsRequestDTO;
import guide.triple.mileage.event.EventHandler;

/**
 * packageName    : guide.triple.mileage.event.impl
 * fileName       : ModifyEventHandler
 * author         : kimdonggyuuuuu
 * date           : 2022/06/30
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/06/30        kimdonggyuuuuu       최초 생성
 */
public class ModifyEventHandler implements EventHandler {


    @Override
    public String getName() {
        return "MOD";
    }

    @Override
    public void handle(EventsRequestDTO requestDTO) {

    }
}
