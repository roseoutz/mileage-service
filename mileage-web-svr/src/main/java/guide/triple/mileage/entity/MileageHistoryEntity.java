package guide.triple.mileage.entity;

import guide.triple.mileage.converter.BooleanToStringConverter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

/**
 * packageName    : guide.triple.mileage.entity
 * fileName       : MileageEntity
 * author         : kimdonggyuuuuu
 * date           : 2022/06/30
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/06/30        kimdonggyuuuuu       최초 생성
 */
@Getter
@Setter
@Entity
@Table(name = "triple_mileage_history")
public class MileageHistoryEntity {

    @Id
    @Column(name = "oid", length = 36)
    private String oid;

    @Column(name = "user_id", length = 36)
    private String userId;

    @Column(name = "place_id", length = 36)
    private String placeId;

    @Column(name = "review_id", length = 36)
    private String reviewId;

    @Convert(converter= BooleanToStringConverter.class)
    @Column(name = "has_text", length = 1)
    private boolean hasText = false;

    @Convert(converter=BooleanToStringConverter.class)
    @Column(name = "has_image", length = 1)
    private boolean hasImage = false;

    @Convert(converter=BooleanToStringConverter.class)
    @Column(name = "has_bonus", length = 1)
    private boolean hasBonus = false;

    @Column(name="insert_time")
    protected long insertTime = System.currentTimeMillis();

    @Column(name="update_time")
    protected long updateTime = this.insertTime;

    @PrePersist
    public void prePersist() {
        oid = UUID.randomUUID().toString();
    }

    @PreUpdate
    public void preUpdate() {
        updateTime = System.currentTimeMillis();
    }

}
