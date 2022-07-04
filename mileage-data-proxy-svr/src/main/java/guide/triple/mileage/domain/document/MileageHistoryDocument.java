package guide.triple.mileage.domain.document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.relational.core.mapping.Column;

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

    private boolean hasText = false;

    private boolean hasImage = false;

    private boolean hasBonus = false;

    protected long insertTime = System.currentTimeMillis();

    protected long updateTime = this.insertTime;

    
}
