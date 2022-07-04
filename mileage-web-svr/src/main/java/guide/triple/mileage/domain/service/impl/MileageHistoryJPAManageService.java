package guide.triple.mileage.domain.service.impl;

import guide.triple.mileage.common.dto.SearchParam;
import guide.triple.mileage.domain.dto.MileageHistoryDTO;
import guide.triple.mileage.domain.entity.MileageHistoryEntity;
import guide.triple.mileage.domain.repository.MileageHistoryRepository;
import guide.triple.mileage.domain.service.MileageHistoryManageService;
import guide.triple.mileage.message.KafkaProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

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

    @Resource(name = "historyProducer")
    private KafkaProducer<String> kafkaProducer;

    private final MileageHistoryRepository historyRepository;

    @Override
    @Transactional
    public void add(MileageHistoryDTO historyDTO) {
        MileageHistoryEntity savedEntity = historyRepository.saveAndFlush(toEntity(historyDTO));
        kafkaProducer.sendMessage(savedEntity.getOid());
    }

    @Override
    public List<MileageHistoryDTO> search(SearchParam searchParam) {
        return historyRepository.findAllByUserId((String) searchParam.getSearchKeyword().get("userId")
                        , PageRequest.of(searchParam.getPage() - 1, searchParam.getPagePerSize(), Sort.by(Sort.Direction.DESC, "insertTime")))
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private MileageHistoryEntity toEntity(MileageHistoryDTO dto) {
        MileageHistoryEntity entity = new MileageHistoryEntity();
        entity.setOid(UUID.randomUUID().toString());
        entity.setUserId(dto.getUserId());
        entity.setPlaceId(dto.getPlaceId());
        entity.setReviewId(dto.getReviewId());
        entity.setActionType(dto.getActionType());
        entity.setHasText(dto.isHasText());
        entity.setHasBonus(dto.isHasBonus());
        entity.setHasImage(dto.isHasImage());

        return entity;
    }

    private MileageHistoryDTO toDto(MileageHistoryEntity entity) {
        return MileageHistoryDTO
                .builder()
                .oid(entity.getOid())
                .userId(entity.getUserId())
                .placeId(entity.getPlaceId())
                .reviewId(entity.getReviewId())
                .hasText(entity.isHasText())
                .hasImage(entity.isHasImage())
                .hasBonus(entity.isHasBonus())
                .actionType(entity.getActionType())
                .insertTime(entity.getInsertTime())
                .updateTime(entity.getUpdateTime())
                .build();
    }
}
