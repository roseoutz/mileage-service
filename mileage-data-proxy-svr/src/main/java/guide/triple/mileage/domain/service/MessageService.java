package guide.triple.mileage.domain.service;

public interface MessageService<T> {

    void sync(T data);
}
