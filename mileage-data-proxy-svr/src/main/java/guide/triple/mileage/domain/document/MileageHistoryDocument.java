package guide.triple.mileage.domain.document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document("triple_mileage_history")
public class MileageHistoryDocument {

    @Id
    private String oid;

    private String userId;

    private String placeId;

    private String reviewId;

    private String actionType;

    private long textPoint = 0;

    private long imagePoint = 0;

    private long bonusPoint = 0;

    private long earnedPoint = 0;

    protected long insertTime = System.currentTimeMillis();

    protected long updateTime = this.insertTime;

    
}
