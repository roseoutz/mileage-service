package guide.triple.mileage.domain.document;

import lombok.*;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document("triple_mileage_user_info")
public class MileageUserInfoDocument {

    @Indexed
    @Id
    private String oid;

    private String userId;

    private long point;
}
