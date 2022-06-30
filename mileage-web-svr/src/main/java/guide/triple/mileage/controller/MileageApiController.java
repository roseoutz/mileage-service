package guide.triple.mileage.controller;

import guide.triple.mileage.dto.EventsRequestDTO;
import guide.triple.mileage.dto.ResponseDTO;
import guide.triple.mileage.service.MileagePointService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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


    @PostMapping(value = "${url.api.mileage.events:/events}")
    public ResponseEntity<ResponseDTO> events(@RequestBody EventsRequestDTO requestDTO) {
        mileagePointService.event(requestDTO);

        return ResponseEntity.ok(ResponseDTO
                .builder()
                .success(true)
                .statusCode(200)
                .build());
    }

}
