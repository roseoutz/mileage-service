package guide.triple.mileage.domain.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Table("triple_mileage_history")
public class MileageHistoryR2Entity implements Persistable<String> {

    @Id
    @Column(value = "oid")
    private String oid;

    @Column(value = "user_id")
    private String userId;

    @Column(value = "place_id")
    private String placeId;

    @Column(value = "review_id")
    private String reviewId;

    @Column(value = "action_type")
    private String actionType;

    @Column(value = "text_point")
    private long textPoint = 0;

    @Column(value = "image_point")
    private long imagePoint = 0;

    @Column(value = "bonus_point")
    private long bonusPoint = 0;

    @Column(value = "earned_point")
    private long earnedPoint = 0;

    @Column(value="insert_time")
    protected long insertTime = System.currentTimeMillis();

    @Column(value="update_time")
    protected long updateTime = this.insertTime;

    @Transient
    private boolean isNew;

    @Override
    public String getId() {
        return oid;
    }

    @Override
    public boolean isNew() {
        return isNew;
    }
}
