package guide.triple.mileage.domain.repository;

import guide.triple.mileage.domain.entity.MileageHistoryR2Entity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface MileageHistoryR2Repository extends ReactiveCrudRepository<MileageHistoryR2Entity, String> {

    Flux<MileageHistoryR2Entity> findAllById(String id);
}
