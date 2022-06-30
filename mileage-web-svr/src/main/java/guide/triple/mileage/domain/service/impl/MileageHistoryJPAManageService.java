package guide.triple.mileage.domain.service.impl;

import guide.triple.mileage.domain.dto.MileageHistoryDTO;
import guide.triple.mileage.domain.entity.MileageHistoryEntity;
import guide.triple.mileage.domain.repository.MileageHistoryRepository;
import guide.triple.mileage.domain.service.MileageHistoryManageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * packageName    : guide.triple.mileage.service.impl
 * fileName       : MileageHistoryJPAManageService
 * author         : kimdonggyuuuuu
 * date           : 2022/06/30
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/06/30        kimdonggyuuuuu       최초 생성
 */

@RequiredArgsConstructor
@Service("mileageHistoryJPAService")
public class MileageHistoryJPAManageService implements MileageHistoryManageService {

    private final MileageHistoryRepository historyRepository;

    @Override
    @Transactional
    public void add(MileageHistoryDTO historyDTO) {
        historyRepository.saveAndFlush(toEntity(historyDTO));
    }

    private MileageHistoryEntity toEntity(MileageHistoryDTO dto) {
        MileageHistoryEntity entity = new MileageHistoryEntity();
        entity.setOid(UUID.randomUUID().toString());
        entity.setUserId(dto.getUserId());
        entity.setPlaceId(dto.getPlaceId());
        entity.setReviewId(dto.getReviewId());
        entity.setHasText(dto.isHasText());
        entity.setHasBonus(dto.isHasBonus());
        entity.setHasImage(dto.isHasImage());

        return entity;
    }
}
