package guide.triple.mileage.web.config;

import com.hazelcast.config.Config;
import com.hazelcast.config.EvictionConfig;
import com.hazelcast.config.IntegrityCheckerConfig;
import com.hazelcast.config.MapConfig;
import com.hazelcast.jet.config.JetConfig;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableCaching
@Configuration
public class HazelcastConfig {

    @Bean
    public Config hazelcastConfigs() {
        return new Config()
                .setInstanceName("config-instance")
                .setJetConfig(new JetConfig().setEnabled(true))
                .setIntegrityCheckerConfig(new IntegrityCheckerConfig().setEnabled(false))
                .addMapConfig(
                        new MapConfig()
                                .setName("points")
                                .setEvictionConfig(new EvictionConfig())
                                .setTimeToLiveSeconds(300)
                );
    }
}
