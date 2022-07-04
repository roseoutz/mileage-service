package guide.triple.mileage.message.impl;

import guide.triple.mileage.message.KafkaProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service("historyProducer")
public class HistoryProducer implements KafkaProducer<String> {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void sendMessage(String historyOid) {
        kafkaTemplate.send("history_sync", historyOid);
    }

}
