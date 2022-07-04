package guide.triple.mileage.domain.service.impl;

import guide.triple.mileage.common.constant.ErrorCode;
import guide.triple.mileage.common.exception.MileageException;
import guide.triple.mileage.domain.document.MileageUserInfoDocument;
import guide.triple.mileage.domain.dto.MileageUserInfoDTO;
import guide.triple.mileage.domain.repository.MileageUserInfoRepository;
import guide.triple.mileage.domain.service.MileageUserInfoManageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service("mileageUserInfoMongoManageService")
public class MileageUserInfoMongoManageService implements MileageUserInfoManageService {

    private final MileageUserInfoRepository userInfoRepository;

    @Override
    public Mono<MileageUserInfoDTO> get(String userId) {
        return userInfoRepository.findById(userId)
                .map(this::toDTO)
                .switchIfEmpty(Mono.error(new MileageException(ErrorCode.ERROR_NOT_EXIST_USER)))
                .onErrorResume(err -> Mono.error(new MileageException(err)));
    }

    public MileageUserInfoDTO toDTO(MileageUserInfoDocument document) {
        return MileageUserInfoDTO
                .builder()
                .userId(document.getUserId())
                .point(document.getPoint())
                .build();
    }
}
