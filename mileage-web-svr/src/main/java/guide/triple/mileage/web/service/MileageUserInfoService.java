package guide.triple.mileage.web.service;

import guide.triple.mileage.common.dto.SearchParam;
import guide.triple.mileage.web.dto.ResponseDTO;

public interface MileageUserInfoService {

    ResponseDTO getHistory(SearchParam searchParam);
}
