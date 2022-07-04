package guide.triple.mileage.web.controller;

import guide.triple.mileage.web.dto.ResponseDTO;
import guide.triple.mileage.web.dto.EventsRequestDTO;
import guide.triple.mileage.web.service.MileagePointService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * packageName    : guide.triple.mileage.controller
 * fileName       : MileageApiController
 * author         : kimdonggyuuuuu
 * date           : 2022/06/30
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/06/30        kimdonggyuuuuu       최초 생성
 */
@Slf4j
@RequiredArgsConstructor
@RestController
public class MileageApiController {
    private final MileagePointService mileagePointService;

    @Operation(summary = "사용자 Clue Mileage 적립 API")
    @PostMapping(value = "/events")
    public ResponseEntity<ResponseDTO> events(@RequestBody EventsRequestDTO requestDTO) {

        log.info("[Event Log] review event request : {}", requestDTO.toString());

        ResponseDTO responseDTO = mileagePointService.event(requestDTO);

        return ResponseEntity.ok(responseDTO);
    }


}
