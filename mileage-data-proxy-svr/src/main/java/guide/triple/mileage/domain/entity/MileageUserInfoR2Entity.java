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
@Table("triple_mileage_user_info")
public class MileageUserInfoR2Entity implements Persistable<String> {

    @Id
    @Column(value = "user_id")
    private String userId;

    @Column("point")
    private long point;

    @Transient
    private boolean isNew;

    @Override
    public String getId() {
        return userId;
    }

    @Override
    public boolean isNew() {
        return isNew;
    }
}
