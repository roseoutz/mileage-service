package guide.triple.mileage.web.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = {"guide.triple.mileage.domain.repository"})
@EntityScan(basePackages = {"guide.triple.mileage.domain.entity"})
public class JPAConfig {
}
