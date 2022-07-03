package guide.triple.mileage.web.controller;

import guide.triple.mileage.common.dto.SearchParam;
import guide.triple.mileage.web.dto.ResponseDTO;
import guide.triple.mileage.web.dto.EventsRequestDTO;
import guide.triple.mileage.web.service.MileagePointService;
import guide.triple.mileage.web.service.MileageUserInfoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    private final MileageUserInfoService mileageUserInfoService;

    @Operation(summary = "사용자 Clue Mileage 적립 API")
    @PostMapping(value = "/events")
    public ResponseEntity<ResponseDTO> events(@RequestBody EventsRequestDTO requestDTO) {

        log.info("[Event Log] review event request : {}", requestDTO.toString());

        ResponseDTO responseDTO = mileagePointService.event(requestDTO);

        return ResponseEntity.ok(responseDTO);
    }

    @Operation(summary = "사용자 Clue Mileage 조회 API")
    @GetMapping(value = "/get/{userId}")
    public ResponseEntity<ResponseDTO> get(@PathVariable String userId) {

        log.info("[Event Log] get user request : {}", userId);

        ResponseDTO responseDTO = mileageUserInfoService.get(userId);
        return ResponseEntity.ok(responseDTO);
    }

    @Operation(summary = "사용자 Club Mileage 적립 이력 조회 API")
    @GetMapping(value = "/history/{userId}/{pagePerSize}/{page}")
    public ResponseEntity<ResponseDTO> history(@PathVariable String userId, @PathVariable(required = false) int pagePerSize, @PathVariable(required = false) int page) {
        SearchParam searchParam = SearchParam.builder()
                .searchKeyword("userId", userId)
                .pagePerSize(pagePerSize)
                .page(page)
                .build();

        ResponseDTO responseDTO = mileageUserInfoService.getHistory(searchParam);

        return ResponseEntity.ok(responseDTO);
    }
}
