package guide.triple.mileage.domain.repository;

import guide.triple.mileage.domain.entity.MileageUserInfoR2Entity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MileageUserInfoR2Repository extends ReactiveCrudRepository<MileageUserInfoR2Entity, String> {
}
