package com.remlists.list.read.infrastructure.spring.config;

import com.google.gson.Gson;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

//@Configuration
//@Profile("!test")
public class KafkaConfig {

//    @Bean
//    @ConditionalOnMissingBean
    public Gson jsonConverter(){
        return new Gson();
    }



/*    @Bean("kafkaMessagePublisher")
    @ConditionalOnMissingBean
    public MessagePublisher kafkaMessagePublisher() {

        System.out.println("### kafka Template " + kafkaTemplate.metrics());
        System.out.println("### eventEntityToJsonEventConverter " + eventEntityToJsonEventConverter.toString());
//        return new KafkaMessagePublisher(kafkaTemplate, eventEntityToJsonEventConverter);
        return new KafkaMessagePublisher();
    }*/

//    @Value(value = "${spring.kafka.bootstrap-servers}")
//    private String bootstrapAddress;

//    @Bean
//    public KafkaAdmin kafkaAdmin() {
//        Map<String, Object> configs = new HashMap<>();
//        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
//        return new KafkaAdmin(configs);
//    }

//    @Bean
    public NewTopic topic1() {
        return new NewTopic("pruebatopic", 1, (short) 1);
    }


}
