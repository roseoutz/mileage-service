package guide.triple.mileage.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;



/**
 * packageName    : guide.triple.mileage.entity
 * fileName       : MileagePointInfoEntity
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
@Table(name = "triple_mileage_point")
public class MileagePointInfoEntity {

    @Id
    @Column(name = "type", length = 10)
    private String type;

    @Column(name = "point", nullable = false)
    private long point = 0;

}
