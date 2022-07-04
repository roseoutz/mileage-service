package guide.triple.mileage.web.controller;

import guide.triple.mileage.common.dto.SearchParam;
import guide.triple.mileage.web.dto.ResponseDTO;
import guide.triple.mileage.web.service.MileageUserInfoService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@RestController
public class MileageUserInfoApiController {

    private final MileageUserInfoService mileageUserInfoService;

    @Operation(summary = "사용자 Clue Mileage 조회 API")
    @GetMapping(value = "/get/{userId}")
    public Mono<ResponseEntity<ResponseDTO>> get(@PathVariable String userId) {

        log.info("[Event Log] get user request : {}", userId);


        return mileageUserInfoService.get(userId)
                .map(ResponseEntity::ok);
    }

    @Operation(summary = "사용자 Club Mileage 적립 이력 조회 API")
    @GetMapping(value = "/history/{userId}/{pagePerSize}/{page}")
    public Mono<ResponseEntity<ResponseDTO>> history(@PathVariable String userId, @PathVariable(required = false) int pagePerSize, @PathVariable(required = false) int page) {
        SearchParam searchParam = SearchParam.builder()
                .searchKeyword("userId", userId)
                .pagePerSize(pagePerSize)
                .page(page)
                .build();

        return mileageUserInfoService.getHistory(searchParam)
                .map(ResponseEntity::ok);
    }
}
