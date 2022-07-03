package guide.triple.mileage.domain.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
        name = "triple_mileage_config",
        indexes = {
                @Index(
                        name = "idx_config_key",
                        columnList = "config_key"
                )
        }
)
public class MileageConfigEntity {

    @Id
    @Column(name = "config_key", length = 32)
    private String configKey;

    @Column(name = "config_value", length = 32, nullable = false)
    private String configValue;

    @Column(name="insert_time")
    protected long insertTime = System.currentTimeMillis();

    @Column(name="update_time")
    protected long updateTime = this.insertTime;

    @PreUpdate
    public void preUpdate() {
        updateTime = System.currentTimeMillis();
    }
}
