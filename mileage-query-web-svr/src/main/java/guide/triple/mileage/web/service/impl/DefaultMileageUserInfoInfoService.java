package guide.triple.mileage.web.service.impl;

import guide.triple.mileage.common.constant.ErrorCode;
import guide.triple.mileage.common.dto.SearchParam;
import guide.triple.mileage.common.exception.MileageException;
import guide.triple.mileage.common.util.StringCheckUtil;
import guide.triple.mileage.domain.service.MileageHistoryManageService;
import guide.triple.mileage.domain.service.MileageUserInfoManageService;
import guide.triple.mileage.web.dto.ResponseDTO;
import guide.triple.mileage.web.service.MileageUserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service("defaultMileageUserInfoService")
public class DefaultMileageUserInfoInfoService implements MileageUserInfoService {

    private final MileageUserInfoManageService userInfoService;

    private final MileageHistoryManageService historyManageService;


    @Override
    public Mono<ResponseDTO> get(String userId) {

        if (!StringCheckUtil.isNonNull(userId)) {
            throw new MileageException(ErrorCode.Error_REQUEST_PARAMETER_INVALID);
        }

         return userInfoService.get(userId)
                 .map(dto -> ResponseDTO
                         .builder()
                         .success(true)
                         .statusCode(200)
                         .result(dto)
                         .build()
                 );
    }

    @Override
    public Mono<ResponseDTO> getHistory(SearchParam searchParam) {

        if (searchParam.getSearchKeyword().containsKey("userId")) {
            return historyManageService.search(searchParam)
                    .collectList()
                    .map(dtoList -> ResponseDTO
                            .builder()
                            .success(true)
                            .statusCode(200)
                            .result(dtoList)
                            .build()
                    );
        }

        throw new MileageException(ErrorCode.Error_REQUEST_PARAMETER_INVALID);
    }

}
