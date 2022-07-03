package guide.triple.mileage.domain.dto;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Getter
@Builder(toBuilder = true)
public class MileageConfigDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String key;
    private String value;
    @Builder.Default
    private long insertTime = System.currentTimeMillis();
    @Builder.Default
    private long updateTime = System.currentTimeMillis();
}
