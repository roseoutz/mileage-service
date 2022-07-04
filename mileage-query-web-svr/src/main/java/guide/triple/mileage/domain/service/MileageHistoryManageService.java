package guide.triple.mileage.domain.service;

import guide.triple.mileage.common.dto.SearchParam;
import guide.triple.mileage.domain.dto.MileageHistoryDTO;
import reactor.core.publisher.Flux;

public interface MileageHistoryManageService {

    Flux<MileageHistoryDTO> search(SearchParam searchParam);
}
