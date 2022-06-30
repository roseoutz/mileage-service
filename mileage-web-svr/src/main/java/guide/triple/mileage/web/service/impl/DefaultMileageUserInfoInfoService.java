package guide.triple.mileage.web.service.impl;

import guide.triple.mileage.domain.dto.MileageUserInfoDTO;
import guide.triple.mileage.domain.service.MileageUserInfoManageService;
import guide.triple.mileage.web.dto.ResponseDTO;
import guide.triple.mileage.web.service.MileageUserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service("defaultMileageUserInfoService")
public class DefaultMileageUserInfoInfoService implements MileageUserInfoService {

    private final MileageUserInfoManageService userInfoService;

    @Override
    public ResponseDTO get(String userId) {
        MileageUserInfoDTO userInfoDTO = userInfoService.get(userId);
        return ResponseDTO
                .builder()
                .success(true)
                .statusCode(200)
                .result(userInfoDTO)
                .build();
    }
}
