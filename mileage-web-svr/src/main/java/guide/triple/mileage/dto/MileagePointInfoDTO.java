package guide.triple.mileage.dto;

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
public class MileagePointInfoDTO {

    private String type;

    @Builder.Default
    private long point = 0;
}
