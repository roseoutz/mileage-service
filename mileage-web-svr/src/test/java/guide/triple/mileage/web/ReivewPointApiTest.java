package guide.triple.mileage.web;

import guide.triple.mileage.common.constant.ActionType;
import guide.triple.mileage.web.controller.MileageApiController;
import guide.triple.mileage.web.dto.EventsRequestDTO;
import guide.triple.mileage.web.dto.ResponseDTO;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;

/**
 * packageName    : guide.triple.mileage.unit
 * fileName       : ReivewPointApiTest
 * author         : kimdonggyuuuuu
 * date           : 2022/07/01
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/07/01        kimdonggyuuuuu       최초 생성
 */
@ActiveProfiles(value = "test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ReivewPointApiTest {

    @Autowired
    private MileageApiController mileageApiController;

    private EventsRequestDTO getEventRequest(ActionType actionType, String userId, String content) {
        return new EventsRequestDTO("REVIEW",
                actionType,
                "240a0658-dc5f-4878-9381-ebb7b2667772",
                UUID.nameUUIDFromBytes(userId.getBytes(StandardCharsets.UTF_8)).toString(),
                "2e4baf1c-5acb-4efb-a1af-eddada31b00f",
                content,
                List.of("e4d1a64e-a531-46de-88d0-ff0ed70c0bb8", "afb0cef2-851d-4a50-bb07-9cc15cbdc332")
                );
    }

    @Test
    @Order(1)
    @DisplayName("등록 시 포인트 적립 API 테스트")
    void add_review_api_test() {
        ResponseEntity<ResponseDTO> responseDTOResponseEntity = mileageApiController.events(getEventRequest(ActionType.ADD, "test", "Good!!"));

        ResponseDTO responseDTO = responseDTOResponseEntity.getBody();

        Assertions.assertEquals(HttpStatus.OK, responseDTOResponseEntity.getStatusCode());
        assert responseDTO != null;
        Assertions.assertTrue(responseDTO.isSuccess());

    }

    @Test
    @Order(2)
    @DisplayName("수정 시 포인트 적립 API 테스트")
    void mod_review_api_test() {
        ResponseEntity<ResponseDTO> responseDTOResponseEntity = mileageApiController.events(getEventRequest(ActionType.MOD, "test", null));

        ResponseDTO responseDTO = responseDTOResponseEntity.getBody();

        Assertions.assertEquals(HttpStatus.OK, responseDTOResponseEntity.getStatusCode());
        assert responseDTO != null;
        Assertions.assertTrue(responseDTO.isSuccess());

    }

    @Test
    @Order(3)
    @DisplayName("삭제 시 포인트 적립 API 테스트")
    void delete_review_api_test() {
        ResponseEntity<ResponseDTO> responseDTOResponseEntity = mileageApiController.events(getEventRequest(ActionType.DELETE, "test", null));

        ResponseDTO responseDTO = responseDTOResponseEntity.getBody();

        Assertions.assertEquals(HttpStatus.OK, responseDTOResponseEntity.getStatusCode());
        assert responseDTO != null;
        Assertions.assertTrue(responseDTO.isSuccess());

    }

}
