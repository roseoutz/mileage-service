package guide.triple.mileage.manage;

import guide.triple.mileage.common.constant.ErrorCode;
import guide.triple.mileage.common.dto.SearchParam;
import guide.triple.mileage.common.exception.MileageException;
import guide.triple.mileage.domain.dto.MileageHistoryDTO;
import guide.triple.mileage.domain.dto.MileageReviewDTO;
import guide.triple.mileage.domain.dto.MileageUserInfoDTO;
import guide.triple.mileage.domain.service.MileageHistoryManageService;
import guide.triple.mileage.domain.service.MileageReviewManageService;
import guide.triple.mileage.domain.service.MileageUserInfoManageService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;

/**
 * packageName    : guide.triple.mileage.unit
 * fileName       : ReviewJPAManageServiceTest
 * author         : kimdonggyuuuuu
 * date           : 2022/07/01
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/07/01        kimdonggyuuuuu       최초 생성
 */
@ActiveProfiles(value = "test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReviewPointTest {

    @Autowired
    private MileageReviewManageService mileageReviewManageService;

    @Autowired
    private MileageUserInfoManageService userInfoManageService;

    @Autowired
    private MileageHistoryManageService historyManageService;

    private String getUUID(String param) {
        return UUID.nameUUIDFromBytes(param.getBytes(StandardCharsets.UTF_8)).toString();
    }

    private MileageReviewDTO getMileageReviewDTO(String idSalt, String placeSalt, String reviewSalt) {
        return MileageReviewDTO
                .builder()
                .userId(getUUID("userId" + idSalt))
                .placeId(getUUID("placeId" + placeSalt))
                .reviewId(getUUID("reviewId" + reviewSalt))
                .hasText(true)
                .hasImage(true)
                .build();
    }

    @Test
    @Order(1)
    @DisplayName("리뷰 등록 및 마일리지 적립 테스트")
    void add_review_first_Test() {
        MileageReviewDTO reviewDTO = getMileageReviewDTO("1", "1", "1");

        mileageReviewManageService.add(reviewDTO);

        MileageUserInfoDTO userInfoDTO = userInfoManageService.get(reviewDTO.getUserId());

        Assertions.assertEquals(3, userInfoDTO.getPoint());
    }

    @Test
    @Order(2)
    @DisplayName("같은 장소에 2개의 리뷰 등록 시 Exception 발생 테스트")
    void add_review_exception_Test() {
        MileageReviewDTO reviewDTO = getMileageReviewDTO("1", "1", "1");

        MileageException e = Assertions.assertThrows(MileageException.class, () -> {
            mileageReviewManageService.add(reviewDTO);
        });

        Assertions.assertEquals(ErrorCode.ERROR_REGISTERED_REVIEW_EXIST.getCode(), e.getErrorCode());
    }

    @Test
    @Order(3)
    @DisplayName("두번째 사용자 리뷰 등록 및 마일리지 적립 테스트")
    void add_review_second_Test() {
        MileageReviewDTO reviewDTO = getMileageReviewDTO("2", "1", "1");

        mileageReviewManageService.add(reviewDTO);

        MileageUserInfoDTO userInfoDTO = userInfoManageService.get(reviewDTO.getUserId());

        Assertions.assertEquals(2, userInfoDTO.getPoint());
    }

    @Test
    @Order(4)
    @DisplayName("리뷰 수정 및 마일리지 수정 테스트")
    void update_reviewTest() {
        MileageReviewDTO reviewDTO = getMileageReviewDTO("1", "1", "1");
        MileageReviewDTO modifiedReviewDTO = reviewDTO.toBuilder()
                .hasImage(false)
                // Test시엔 첫 리뷰이므로 무조건 True이다.
                .hasBonus(true)
                .build();

        mileageReviewManageService.update(modifiedReviewDTO);

        MileageUserInfoDTO userInfoDTO = userInfoManageService.get(modifiedReviewDTO.getUserId());

        Assertions.assertEquals(2, userInfoDTO.getPoint());
    }



    @Test
    @Order(5)
    @DisplayName("리뷰 삭제 및 마일리지 회수 테스트")
    void delete_reviewTest() {
        MileageReviewDTO reviewDTO = getMileageReviewDTO("1", "1", "1");
        mileageReviewManageService.delete(reviewDTO);

        MileageUserInfoDTO userInfoDTO = userInfoManageService.get(reviewDTO.getUserId());

        Assertions.assertEquals(0, userInfoDTO.getPoint());
    }

    @Test
    @Order(6)
    @DisplayName("삭제된 다음 리뷰 bonus 획득 여부 테스트")
    void delete_after_change_bonus_reviewTest() {
        MileageReviewDTO reviewDTO = getMileageReviewDTO("2", "1", "1");

        MileageUserInfoDTO userInfoDTO = userInfoManageService.get(reviewDTO.getUserId());

        Assertions.assertEquals(3, userInfoDTO.getPoint());
    }

    @Test
    @Order(7)
    @DisplayName("리뷰 포인트 적립 히스토리 테스트")
    void review_history_test(){
        String userUid = getUUID("userID1");
        SearchParam searchParam = SearchParam.builder().searchKeyword("userId", userUid).build();

        List<MileageHistoryDTO> list= historyManageService.search(searchParam);

        Assertions.assertEquals(3, list.size());
    }

}
