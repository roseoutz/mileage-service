package guide.triple.mileage.domain.repository;

import guide.triple.mileage.domain.entity.MileageConfigEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MileageConfigRepository extends JpaRepository<MileageConfigEntity, String>, JpaSpecificationExecutor<MileageConfigEntity> {
}
