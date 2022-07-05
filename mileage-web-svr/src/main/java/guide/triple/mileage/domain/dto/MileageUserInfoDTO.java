package guide.triple.mileage.domain.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(toBuilder = true)
public class MileageUserInfoDTO {

    private String userId;

    @Builder.Default
    private long point = 0;
}
