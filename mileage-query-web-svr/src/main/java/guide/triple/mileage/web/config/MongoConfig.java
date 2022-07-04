package guide.triple.mileage.web.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

@RequiredArgsConstructor
@Configuration
public class MongoConfig {

    private final MongoMappingContext mongoMappingContext;

    @Bean
    public MappingMongoConverter reactiveMappingMongoConverter() {
        MappingMongoConverter converter = new MappingMongoConverter(ReactiveMongoTemplate.NO_OP_REF_RESOLVER, mongoMappingContext);

        converter.setTypeMapper(new DefaultMongoTypeMapper());
        return converter;
    }
}
