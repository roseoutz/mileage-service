package guide.triple.mileage.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * packageName    : guide.triple.mileage.dto
 * fileName       : MileagePointInfoDTO
 * author         : kimdonggyuuuuu
 * date           : 2022/06/30
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/06/30        kimdonggyuuuuu       최초 생성
 */
@AllArgsConstructor
@Getter
@Builder(toBuilder = true)
public class MileageReviewDTO {

    private String userId;

    private String placeId;

    private String reviewId;

    @Builder.Default
    private boolean text = false;

    @Builder.Default
    private long textPoint = 0;

    @Builder.Default
    private boolean image = false;

    @Builder.Default
    private long imagePoint = 0;

    @Builder.Default
    private boolean bonus = false;

    @Builder.Default
    private long bonusPoint = 0;
}
