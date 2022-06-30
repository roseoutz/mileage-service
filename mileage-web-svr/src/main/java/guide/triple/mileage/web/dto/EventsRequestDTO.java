package guide.triple.mileage.web.dto;

import guide.triple.mileage.common.constant.ActionType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

/**
 * packageName    : guide.triple.mileage.dto
 * fileName       : EventsRequestDTO
 * author         : kimdonggyuuuuu
 * date           : 2022/06/30
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/06/30        kimdonggyuuuuu       최초 생성
 */
@Getter
@AllArgsConstructor
public class EventsRequestDTO {

    private String type;
    private ActionType action;
    private String reviewId;
    private String userId;
    private String placeId;
    private String content;
    private List<String> attachedPhotoIds;
}
