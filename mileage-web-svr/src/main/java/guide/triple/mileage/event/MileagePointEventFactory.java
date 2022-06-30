package guide.triple.mileage.event;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * packageName    : guide.triple.mileage.event
 * fileName       : MileagePointEventFactory
 * author         : kimdonggyuuuuu
 * date           : 2022/06/30
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/06/30        kimdonggyuuuuu       최초 생성
 */
public class MileagePointEventFactory {

    private final Map<String, EventHandler> eventHandlerMap;

    private MileagePointEventFactory(Builder builder){
        eventHandlerMap = Map.copyOf(builder.eventHandlerMap);
    }

    public Optional<EventHandler> get(String action) {
        return Optional.ofNullable(eventHandlerMap.get(action));
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final Map<String, EventHandler> eventHandlerMap = new HashMap<>();

        public Builder set(EventHandler eventHandler) {
            this.eventHandlerMap.put(eventHandler.getName(), eventHandler);
            return this;
        }

        public MileagePointEventFactory build() {
            return new MileagePointEventFactory(this);
        }
    }
}
