package guide.triple.mileage.domain.service.impl;

import guide.triple.mileage.common.dto.SearchParam;
import guide.triple.mileage.domain.document.MileageHistoryDocument;
import guide.triple.mileage.domain.dto.MileageHistoryDTO;
import guide.triple.mileage.domain.repository.MileageHistoryRepository;
import guide.triple.mileage.domain.service.MileageHistoryManageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
@Service("mileageHistoryMongoManageService")
public class MileageHistoryMongoManageService implements MileageHistoryManageService {

    private final MileageHistoryRepository historyRepository;

    @Override
    public Flux<MileageHistoryDTO> search(SearchParam searchParam) {
        String userId = (String) searchParam.getSearchKeyword().get("userId");
        return historyRepository.findAllByUserId(userId,
                PageRequest.of(searchParam.getPage() - 1, searchParam.getPagePerSize(), Sort.by(Sort.Direction.DESC, "insertTime")))
                .map(this::toDTO);
    }

    private MileageHistoryDTO toDTO(MileageHistoryDocument document) {
        return MileageHistoryDTO
                .builder()
                .oid(document.getOid())
                .userId(document.getUserId())
                .reviewId(document.getReviewId())
                .placeId(document.getPlaceId())
                .actionType(document.getActionType())
                .hasText(document.isHasText())
                .hasImage(document.isHasImage())
                .hasBonus(document.isHasBonus())
                .insertTime(document.getInsertTime())
                .updateTime(document.getUpdateTime())
                .build();
    }
}
