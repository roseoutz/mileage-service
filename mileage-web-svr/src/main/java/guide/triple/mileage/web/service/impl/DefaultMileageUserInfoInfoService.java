package guide.triple.mileage.web.service.impl;

import guide.triple.mileage.common.dto.SearchParam;
import guide.triple.mileage.domain.dto.MileageHistoryDTO;
import guide.triple.mileage.domain.service.MileageHistoryManageService;
import guide.triple.mileage.web.dto.ResponseDTO;
import guide.triple.mileage.web.service.MileageUserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service("defaultMileageUserInfoService")
public class DefaultMileageUserInfoInfoService implements MileageUserInfoService {

    private final MileageHistoryManageService historyService;


    @Override
    public ResponseDTO getHistory(SearchParam searchParam) {
        List<MileageHistoryDTO> list = historyService.search(searchParam);
        return ResponseDTO
                .builder()
                .success(true)
                .statusCode(200)
                .result(list)
                .build();
    }
}
