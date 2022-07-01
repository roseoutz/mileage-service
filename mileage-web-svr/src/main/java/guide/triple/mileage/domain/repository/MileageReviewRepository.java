package guide.triple.mileage.domain.repository;

import guide.triple.mileage.domain.entity.MileageReviewEntity;
import guide.triple.mileage.domain.entity.composite.MileageId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * packageName    : guide.triple.mileage.repository
 * fileName       : MileagePointRepository
 * author         : kimdonggyuuuuu
 * date           : 2022/06/30
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/06/30        kimdonggyuuuuu       최초 생성
 */
@Repository
public interface MileageReviewRepository extends JpaRepository<MileageReviewEntity, MileageId> {

    List<MileageReviewEntity> findByPlaceId(String placeId);

    boolean existsByPlaceId(String placeId);

    Optional<MileageReviewEntity> findFirstByPlaceIdOrderByInsertTimeAsc(String placeId);

}
