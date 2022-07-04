package guide.triple.mileage.domain.service.impl;

import guide.triple.mileage.domain.document.MileageUserInfoDocument;
import guide.triple.mileage.domain.entity.MileageUserInfoR2Entity;
import guide.triple.mileage.domain.repository.MileageUserInfoMongoRepository;
import guide.triple.mileage.domain.repository.MileageUserInfoR2Repository;
import guide.triple.mileage.domain.service.MileageUserInfoMessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@Service("mileageUserInfoKafkaMessageService")
public class MileageUserInfoKafkaMessageService implements MileageUserInfoMessageService {

    private final MileageUserInfoMongoRepository mongoRepository;

    private final MileageUserInfoR2Repository r2Repository;

    @Override
    public void sync(String userId) {
        r2Repository.findById(userId)
                .flatMap(this::saveMongoRepository)
                .subscribe(
                        documentMono -> log.info("{} data Insert document", userId),
                        throwable -> log.error("{} data insert error : {}", userId, throwable.getMessage(), throwable)
                        );
    }

    private Mono<MileageUserInfoDocument> saveMongoRepository(MileageUserInfoR2Entity mileageUserInfoR2Entity) {
        MileageUserInfoDocument document = toDocument(mileageUserInfoR2Entity);
        document.setUserId(mileageUserInfoR2Entity.getUserId());
        document.setPoint(mileageUserInfoR2Entity.getPoint());
        return mongoRepository.save(document);
    }

    private MileageUserInfoDocument toDocument(MileageUserInfoR2Entity mileageUserInfoR2Entity) {
        MileageUserInfoDocument document = new MileageUserInfoDocument();
        document.setUserId(mileageUserInfoR2Entity.getUserId());
        document.setPoint(mileageUserInfoR2Entity.getPoint());
        return document;
    }
}
