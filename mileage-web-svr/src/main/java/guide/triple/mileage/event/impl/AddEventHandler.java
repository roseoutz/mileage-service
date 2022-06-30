package guide.triple.mileage.event.impl;

import guide.triple.mileage.dto.EventsRequestDTO;
import guide.triple.mileage.event.EventHandler;

/**
 * packageName    : guide.triple.mileage.event.impl
 * fileName       : AddEventHandler
 * author         : kimdonggyuuuuu
 * date           : 2022/06/30
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/06/30        kimdonggyuuuuu       최초 생성
 */
public class AddEventHandler implements EventHandler {

    @Override
    public String getName() {
        return "ADD";
    }

    @Override
    public void handle(EventsRequestDTO requestDTO) {

        if (requestDTO.getContent() != null && requestDTO.getContent().length() > 0) {
        }


    }

    private boolean isFirstReview(String reviewId) {
        return false;
    }
}
