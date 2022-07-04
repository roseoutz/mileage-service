package guide.triple.mileage.domain.service.impl;

import guide.triple.mileage.domain.document.MileageHistoryDocument;
import guide.triple.mileage.domain.entity.MileageHistoryR2Entity;
import guide.triple.mileage.domain.repository.MileageHistoryMongoRepository;
import guide.triple.mileage.domain.repository.MileageHistoryR2Repository;
import guide.triple.mileage.domain.service.MileageHistoryMessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@Service("mileageHistoryKafkaMessageService")
public class MileageHistoryKafkaMessageService implements MileageHistoryMessageService {

    private final MileageHistoryR2Repository r2Repository;
    private final MileageHistoryMongoRepository mongoRepository;

    @Override
    public void sync(String data) {
        r2Repository.findById(data)
                .flatMap(this::saveMongoRepository)
                .subscribe(
                        documentMono -> log.info("{} data Insert document", data),
                        throwable -> log.error("{} data insert error : {}", data, throwable.getMessage(), throwable)
                );
    }

    private Mono<MileageHistoryDocument> saveMongoRepository(MileageHistoryR2Entity mileageUserInfoR2Entity) {
        MileageHistoryDocument document = toDocument(mileageUserInfoR2Entity);
        return mongoRepository.save(document);
    }

    private MileageHistoryDocument toDocument(MileageHistoryR2Entity mileageUserInfoR2Entity) {
        MileageHistoryDocument document = new MileageHistoryDocument();
        document.setOid(mileageUserInfoR2Entity.getOid());
        document.setUserId(mileageUserInfoR2Entity.getUserId());
        document.setPlaceId(mileageUserInfoR2Entity.getPlaceId());
        document.setReviewId(mileageUserInfoR2Entity.getReviewId());
        document.setActionType(mileageUserInfoR2Entity.getActionType());
        document.setHasText("Y".equalsIgnoreCase(mileageUserInfoR2Entity.getHasText()));
        document.setHasImage("Y".equalsIgnoreCase(mileageUserInfoR2Entity.getHasImage()));
        document.setHasBonus("Y".equalsIgnoreCase(mileageUserInfoR2Entity.getHasBonus()));
        document.setInsertTime(mileageUserInfoR2Entity.getInsertTime());
        document.setUpdateTime(mileageUserInfoR2Entity.getUpdateTime());

        return document;
    }

}
