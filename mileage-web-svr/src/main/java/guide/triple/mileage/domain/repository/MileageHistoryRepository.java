package guide.triple.mileage.domain.repository;

import guide.triple.mileage.domain.entity.MileageHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * packageName    : guide.triple.mileage.repository
 * fileName       : MileageHistoryRepository
 * author         : kimdonggyuuuuu
 * date           : 2022/06/30
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/06/30        kimdonggyuuuuu       최초 생성
 */
@Repository
public interface MileageHistoryRepository extends JpaRepository<MileageHistoryEntity, String>, JpaSpecificationExecutor<MileageHistoryEntity> {
}
