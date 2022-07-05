package guide.triple.mileage.domain.dto;

import guide.triple.mileage.common.constant.ActionType;
import lombok.Builder;
import lombok.Getter;

/**
 * packageName    : guide.triple.mileage.dto
 * fileName       : MileageHistoryDTO
 * author         : kimdonggyuuuuu
 * date           : 2022/06/30
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/06/30        kimdonggyuuuuu       최초 생성
 */
@Getter
@Builder(toBuilder = true)
public class MileageHistoryDTO {

    private String oid;

    private String userId;

    private String placeId;

    private String reviewId;

    private ActionType actionType;

    @Builder.Default
    private long textPoint = 0;

    @Builder.Default
    private long imagePoint = 0;

    @Builder.Default
    private long bonusPoint = 0;

    @Builder.Default
    private long earnedPoint = 0;

    @Builder.Default
    private long insertTime = System.currentTimeMillis();

    @Builder.Default
    private long updateTime = System.currentTimeMillis();
}
