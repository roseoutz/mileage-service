package guide.triple.mileage.config;

import guide.triple.mileage.event.MileagePointEventFactory;
import guide.triple.mileage.event.impl.AddEventHandler;
import guide.triple.mileage.event.impl.DeleteEventHandler;
import guide.triple.mileage.event.impl.ModifyEventHandler;
import guide.triple.mileage.repository.MileageReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * packageName    : guide.triple.mileage.config
 * fileName       : CustomBeanConfig
 * author         : kimdonggyuuuuu
 * date           : 2022/06/30
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/06/30        kimdonggyuuuuu       최초 생성
 */
@RequiredArgsConstructor
@Configuration
public class CustomBeanConfig {

    @Bean(name = "mileagePointEventFactory")
    public MileagePointEventFactory mileagePointEventFactory() {

        return MileagePointEventFactory.builder()
                .set(new AddEventHandler())
                .set(new DeleteEventHandler())
                .set(new ModifyEventHandler())
                .build();
    }
}
