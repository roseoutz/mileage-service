package guide.triple.filter;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class GlobalLoggingFilter extends AbstractGatewayFilterFactory<GlobalLoggingFilter.Config> {

    public GlobalLoggingFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (((exchange, chain) -> {
            log.info("[Mileage-GATEWAY] baseMessage : {}", config.getBaseMessage());

            if (config.isPreLogger()) {
                log.info("[Mileage-GATEWAY] Global Filter Request : {}", exchange.getRequest());
            }

            return chain.filter(exchange)
                    .then(Mono.fromRunnable(() -> {
                        if (config.isPostLogger()) {
                            log.info("[Mileage-GATEWAY] Global Filter Request : {}", exchange.getResponse());
                        }
                    }));
        }));
    }

    @Data
    public static class Config {

        private String baseMessage;
        private boolean preLogger;
        private boolean postLogger;
    }

}
