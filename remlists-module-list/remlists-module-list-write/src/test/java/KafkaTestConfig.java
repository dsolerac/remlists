import com.google.gson.Gson;
import com.google.gson.JsonDeserializer;
import kafka.server.KafkaConfig;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.test.EmbeddedKafkaBroker;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

//@TestConfiguration
//@Profile("test")

//@EnableKafka
public class KafkaTestConfig {

/*


//    @Bean
    public EmbeddedKafkaBroker embeddedKafkaBroker() {

//        return new EmbeddedKafkaBroker(1);

        return new EmbeddedKafkaBroker(1,false,2,"pruebatopic")
                .brokerProperties(Collections.singletonMap(KafkaConfig.LogDirProp(), "/tmp/EmbeddedKafka"));

    }

//    @Bean
//    @ConditionalOnMissingBean
    public Gson jsonConverter(){
        return new Gson();
    }


//    @Bean
    public static ConsumerFactory consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfigs(), new StringDeserializer(), new StringDeserializer());
    }


//    @Bean
    public static Map<String, Object> consumerConfigs() {
        Map<String, Object> consumerProps = new HashMap<>();
        consumerProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        consumerProps.put(ConsumerConfig.GROUP_ID_CONFIG,"kafka_test");
        consumerProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        consumerProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        return consumerProps;
    }

//    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {

        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());

        return factory;
    }
*/

/*    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, embeddedKafkaBroker().getBrokersAsString());
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic topic1() {
        return new NewTopic("pruebatopic2", 1, (short) 1);
    }*/



}
