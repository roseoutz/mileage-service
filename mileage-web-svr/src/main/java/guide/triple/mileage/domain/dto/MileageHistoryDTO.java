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
    private boolean hasText = false;

    @Builder.Default
    private boolean hasImage = false;

    @Builder.Default
    private boolean hasBonus = false;

    @Builder.Default
    private long insertTime = System.currentTimeMillis();

    @Builder.Default
    private long updateTime = System.currentTimeMillis();
}
