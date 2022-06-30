package guide.triple.mileage.domain.repository;

import guide.triple.mileage.domain.entity.MileageUserInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * packageName    : guide.triple.mileage.repository
 * fileName       : MileageUserInfoRepository
 * author         : kimdonggyuuuuu
 * date           : 2022/06/30
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/06/30        kimdonggyuuuuu       최초 생성
 */
@Repository
public interface MileageUserInfoRepository extends JpaRepository<MileageUserInfoEntity, String> {
}
