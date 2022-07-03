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
    private boolean hasText = false;

    @Builder.Default
    private boolean hasImage = false;

    @Builder.Default
    private boolean hasBonus = false;
}
