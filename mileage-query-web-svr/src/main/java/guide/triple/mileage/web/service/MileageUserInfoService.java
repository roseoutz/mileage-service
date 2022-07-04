package guide.triple.mileage.web.service;

import guide.triple.mileage.common.dto.SearchParam;
import guide.triple.mileage.web.dto.ResponseDTO;
import reactor.core.publisher.Mono;

public interface MileageUserInfoService {
    Mono<ResponseDTO> get(String userId);

    Mono<ResponseDTO> getHistory(SearchParam searchParam);
}
