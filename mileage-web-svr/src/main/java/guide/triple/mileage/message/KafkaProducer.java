package guide.triple.mileage.message;

public interface KafkaProducer<T> {

    void sendMessage(T message);
}
