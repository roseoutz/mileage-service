package guide.triple.mileage.domain.repository;

import guide.triple.mileage.domain.document.MileageHistoryDocument;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface MileageHistoryRepository extends ReactiveMongoRepository<MileageHistoryDocument, String> {

    Flux<MileageHistoryDocument> findAllByUserId(String userId, Pageable pageable);
}
