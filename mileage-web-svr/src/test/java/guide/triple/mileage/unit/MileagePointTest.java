package guide.triple.mileage.unit;

import guide.triple.mileage.entity.MileageReviewEntity;
import guide.triple.mileage.entity.composite.MileageId;
import guide.triple.mileage.repository.MileageReviewRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

/**
 * packageName    : guide.triple.mileage.unit
 * fileName       : MileagePointTest
 * author         : kimdonggyuuuuu
 * date           : 2022/06/30
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/06/30        kimdonggyuuuuu       최초 생성
 */
@SpringBootTest
public class MileagePointTest {

    @Autowired
    MileageReviewRepository mileageReviewRepository;

    @Test
    public void addTest() {
        MileageReviewEntity reviewEntity = new MileageReviewEntity();

        MileageId mileageId = new MileageId(UUID.randomUUID().toString(), UUID.randomUUID().toString());

        reviewEntity.setOid(mileageId);
        reviewEntity.setReviewId(UUID.randomUUID().toString());
        reviewEntity.setHasBonus(true);
        reviewEntity.setHasImage(true);
        reviewEntity.setHasText(true);

        MileageReviewEntity savedEntity = mileageReviewRepository.saveAndFlush(reviewEntity);

        Assertions.assertEquals(savedEntity.getOid(), reviewEntity.getOid());
    }

}
