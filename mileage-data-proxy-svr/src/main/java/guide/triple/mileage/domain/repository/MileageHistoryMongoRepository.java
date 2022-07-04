package guide.triple.mileage.domain.repository;

import guide.triple.mileage.domain.document.MileageHistoryDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MileageHistoryMongoRepository extends ReactiveMongoRepository<MileageHistoryDocument, String> {
}
