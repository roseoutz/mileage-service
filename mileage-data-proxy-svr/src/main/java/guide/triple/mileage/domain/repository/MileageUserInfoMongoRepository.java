package guide.triple.mileage.domain.repository;

import guide.triple.mileage.domain.document.MileageUserInfoDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MileageUserInfoMongoRepository extends ReactiveMongoRepository<MileageUserInfoDocument, String> {
}
