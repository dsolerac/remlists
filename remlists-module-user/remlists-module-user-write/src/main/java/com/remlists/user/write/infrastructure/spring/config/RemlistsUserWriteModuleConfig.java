package com.remlists.user.write.infrastructure.spring.config;


import com.google.gson.Gson;
import com.remlists.shared.infrastructure.converter.EventEntityToJsonEventConverter;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@PropertySources({
        @PropertySource("classpath:user-write-application.properties"),
        @PropertySource("classpath:user-write-application-${spring.profiles.active}.properties")})
@Import({RemlistsUsersDomainServicesConfig.class, 
        UserWriteDataBaseConnectionConfig.class
//        ,KafkaConfig.class
        })
@ComponentScan({"com.remlists.user.domain",
                "com.remlists.user.write.application",
                "com.remlists.user.write.infrastructure"})
public class RemlistsUserWriteModuleConfig {


    private Logger LOG = LoggerFactory.getLogger(RemlistsUserWriteModuleConfig.class);


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

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean("eventEntityToJsonEventConverter")
    @ConditionalOnMissingBean
    public EventEntityToJsonEventConverter eventEntityToJsonEventConverter() {
        return new EventEntityToJsonEventConverter(jsonConverter);
    }

//    @Bean
//    public JdbcSagaStore sagaStoreListWrite(ConnectionProvider connectionProvider, Serializer serializer) {
//        return JdbcSagaStore.builder().connectionProvider(connectionProvider).sqlSchema(new GenericSagaSqlSchema()).serializer(serializer).build();
//    }

}
