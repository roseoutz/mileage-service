package guide.triple.mileage.message.consumer;

import guide.triple.mileage.domain.service.MileageHistoryMessageService;
import guide.triple.mileage.domain.service.MileageUserInfoMessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service("mileageUserInfoConsumer")
public class MileageUserInfoConsumer {

    private final MileageUserInfoMessageService mileageUserInfoMessageService;
    private final MileageHistoryMessageService mileageHistoryMessageService;

    @KafkaListener(topics = "user_sync", groupId = "mileage-user")
    public void userSync(String message) {
        log.info("[Message Consumer] user sync request user id : {}", message);
        mileageUserInfoMessageService.sync(message);
    }

    @KafkaListener(topics = "history_sync", groupId = "mileage-history")
    public void historySync(String message) {
        log.info("[Message Consumer] history sync request history oid : {}", message);
        mileageHistoryMessageService.sync(message);
    }

}
