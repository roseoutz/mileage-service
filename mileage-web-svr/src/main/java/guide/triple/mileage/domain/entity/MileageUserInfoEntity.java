package guide.triple.mileage.domain.entity;

import lombok.*;

import javax.persistence.*;


/**
 * packageName    : guide.triple.mileage.entity
 * fileName       : MileageUserInfoEntity
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
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
        name = "triple_mileage_user_info",
        indexes = {
                @Index(
                        name = "idx_user_pk",
                        columnList = "user_id"
                )
        }
)
public class MileageUserInfoEntity {

    @Id
    @Column(name = "user_id", length = 36)
    private String userId;

    @Column(name = "point")
    private long point = 0;


}
