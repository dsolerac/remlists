package com.remlists.list.read.infrastructure.spring.config;


import com.google.gson.Gson;
import com.remlists.shared.infrastructure.converter.EventEntityToJsonEventConverter;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.*;

@Configuration
@PropertySources({
        @PropertySource("classpath:list-read-application.properties"),
        @PropertySource("classpath:list-read-application-${spring.profiles.active}.properties")})
@Import({ListReadDataBaseConnectionConfig.class
//        , KafkaConfig.class
        })
@ComponentScan({"com.remlists.list.domain"
        , "com.remlists.list.read.application"
        , "com.remlists.list.read.infrastructure"})
public class RemlistsListReadModuleConfig {

    private Logger LOG = LoggerFactory.getLogger(RemlistsListReadModuleConfig.class);

    @Autowired
    private Gson jsonConverter;

    @Bean
    @ConditionalOnMissingBean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();

        mapper.getConfiguration()
//                .setMatchingStrategy(MatchingStrategies.STANDARD)
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);

        return mapper;

    }

    @Bean("eventEntityToJsonEventConverter")
    @ConditionalOnMissingBean
    public EventEntityToJsonEventConverter eventEntityToJsonEventConverter() {
        return new EventEntityToJsonEventConverter(jsonConverter);
    }


//    @Bean
//    public JdbcSagaStore sagaStoreListRead(ConnectionProvider connectionProvider, Serializer serializer) {
//        return JdbcSagaStore.builder().connectionProvider(connectionProvider).sqlSchema(new GenericSagaSqlSchema()).serializer(serializer).build();
//    }




}
