package guide.triple.mileage.web.service;

import guide.triple.mileage.web.dto.ResponseDTO;

public interface MileageUserInfoService {

    ResponseDTO get(String userId);
}
