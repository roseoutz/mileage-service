package guide.triple.mileage.domain.entity;

import guide.triple.mileage.common.converter.BooleanToStringConverter;
import guide.triple.mileage.domain.entity.composite.MileageId;
import lombok.*;

import javax.persistence.*;

/**
 * packageName    : guide.triple.mileage.entity
 * fileName       : MileageReviewEntity
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
@IdClass(MileageId.class)
@Entity
@Table(
        name = "triple_mileage_review",
        indexes = {
            @Index(
                    name = "idx_review_place_id",
                    columnList = "place_id"
            )
        }
)
public class MileageReviewEntity {

    @Id
    @Column(name = "user_id", length = 36)
    private String userId;

    @Id
    @Column(name = "place_id", length = 36)
    private String placeId;

    @Column(name = "review_id", length = 36, nullable = false)
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

}
