package guide.triple.mileage.web.controller;

import guide.triple.mileage.web.dto.ResponseDTO;
import guide.triple.mileage.web.dto.EventsRequestDTO;
import guide.triple.mileage.web.service.MileagePointService;
import guide.triple.mileage.web.service.MileageUserInfoService;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
@RestController
public class MileageApiController {
    private final MileagePointService mileagePointService;
    private final MileageUserInfoService mileageUserInfoService;

    @PostMapping(value = "/events")
    public ResponseEntity<ResponseDTO> events(@RequestBody EventsRequestDTO requestDTO) {
        ResponseDTO responseDTO = mileagePointService.event(requestDTO);

        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping(value = "/get/{userId}")
    public ResponseEntity<ResponseDTO> get(@PathVariable String userId) {
        ResponseDTO responseDTO = mileageUserInfoService.get(userId);
        return ResponseEntity.ok(responseDTO);
    }

}
