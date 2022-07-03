package guide.triple.mileage.web;

import guide.triple.mileage.common.constant.ActionType;
import guide.triple.mileage.domain.dto.MileageUserInfoDTO;
import guide.triple.mileage.web.controller.MileageApiController;
import guide.triple.mileage.web.dto.EventsRequestDTO;
import guide.triple.mileage.web.dto.ResponseDTO;
import guide.triple.mileage.web.service.MileagePointService;
import guide.triple.mileage.web.service.MileageUserInfoService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
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
    @DisplayName("포인트 적립 API 테스트")
    void add_review_api_test() {
        ResponseEntity<ResponseDTO> responseDTOResponseEntity = mileageApiController.events(getEventRequest(ActionType.ADD, "test", "Good!!"));

        ResponseDTO responseDTO = responseDTOResponseEntity.getBody();

        Assertions.assertEquals(HttpStatus.OK, responseDTOResponseEntity.getStatusCode());
        assert responseDTO != null;
        Assertions.assertTrue(responseDTO.isSuccess());

    }

    @Test
    @Order(2)
    @DisplayName("포인트 적립 API 테스트")
    void mod_review_api_test() {
        ResponseEntity<ResponseDTO> responseDTOResponseEntity = mileageApiController.events(getEventRequest(ActionType.MOD, "test", null));

        ResponseDTO responseDTO = responseDTOResponseEntity.getBody();

        Assertions.assertEquals(HttpStatus.OK, responseDTOResponseEntity.getStatusCode());
        assert responseDTO != null;
        Assertions.assertTrue(responseDTO.isSuccess());

    }

    @Test
    @Order(3)
    @DisplayName("포인트 적립 API 테스트")
    void delete_review_api_test() {
        ResponseEntity<ResponseDTO> responseDTOResponseEntity = mileageApiController.events(getEventRequest(ActionType.DELETE, "test", null));

        ResponseDTO responseDTO = responseDTOResponseEntity.getBody();

        Assertions.assertEquals(HttpStatus.OK, responseDTOResponseEntity.getStatusCode());
        assert responseDTO != null;
        Assertions.assertTrue(responseDTO.isSuccess());

    }

    @Test
    @Order(4)
    @DisplayName("사용자 포인트 조회 API 테스트")
    void get_user_point_test() {
        ResponseEntity<ResponseDTO> responseDTOResponseEntity = mileageApiController.get(UUID.nameUUIDFromBytes("test".getBytes(StandardCharsets.UTF_8)).toString());

        ResponseDTO responseDTO = responseDTOResponseEntity.getBody();
        assert responseDTO != null;
        MileageUserInfoDTO mileageUserInfoDTO = (MileageUserInfoDTO) responseDTO.getResult();
        Assertions.assertEquals(HttpStatus.OK, responseDTOResponseEntity.getStatusCode());
        Assertions.assertTrue(responseDTO.isSuccess());
        Assertions.assertEquals(0, mileageUserInfoDTO.getPoint());
    }

}
