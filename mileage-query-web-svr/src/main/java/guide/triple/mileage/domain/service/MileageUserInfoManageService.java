package guide.triple.mileage.domain.service;

import guide.triple.mileage.domain.dto.MileageUserInfoDTO;
import reactor.core.publisher.Mono;

public interface MileageUserInfoManageService {

    Mono<MileageUserInfoDTO> get(String userId);

}
