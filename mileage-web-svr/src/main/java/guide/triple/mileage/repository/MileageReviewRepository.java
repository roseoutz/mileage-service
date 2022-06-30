package guide.triple.mileage.repository;

import guide.triple.mileage.entity.MileageReviewEntity;
import guide.triple.mileage.entity.composite.MileageId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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

    Optional<MileageReviewEntity> findByPlaceId(String placeId);

    Optional<MileageReviewEntity> findByReviewId(String reviewId);

}
