package com.remlists.list.write.infrastructure;

import com.remlists.shared.domain.events.MessagePublisher;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;


@SpringBootTest
//@RunWith(SpringRunner.class)

@Tag("IntegrationTest")
@DisplayName("Integration for testing conversion envent entity into a JSON event")

@DirtiesContext
//@EnableKafka
//@EmbeddedKafka( partitions = 2,
//        topics = {"pruebatopic"},
//        brokerProperties = { "listeners=PLAINTEXT://localhost:${kafka.broker.port:9092}",
//                "auto.create.topics.enable=${kafka.broker.topics-enable:true}"}
//)
public class SendCreateNewRemlistEventTest {

    private Logger LOG = LoggerFactory.getLogger(SendCreateNewRemlistEventTest.class);



    /* =========================

    REPENSAR EL TEST, PORQUE NO TIENE SENTIDO TESTEAR INTEGRANDO CON KAFKA,
    ES MEJOR MOCKEAR LOS ENVIOS O RECEPCIONES DE EVENTOS, DANDO POR HECHO
    QUE KAFKA LOS ENVÍA.

    ============================ */

//    @Value("${remlists.list.config.host.name}")
//    private String hostName;

//    @Autowired
//    @Qualifier("listWriteKafkaMessagePublisher")
//    private MessagePublisher publisher;

//    @Autowired
//    public EmbeddedKafkaBroker embeddedKafka ;

//    @Autowired
//    private KafkaTemplate kafkaTemplate;

//    @Autowired
//    private EventEntityToJsonEventConverter eventEntityToJsonEventConverter;


//    @BeforeClass
//    public static void setup() {
//
//        System.out.println("Antes de clase");
//
//        Map<String, Object> consumerProps = KafkaTestUtils.consumerProps("testGroup", "true", embeddedKafka);
//
////        System.setProperty("spring.kafka.bootstrap-servers", kafkaRule.getEmbeddedKafka().getBrokersAsString());
//        System.setProperty("spring.kafka.bootstrap-servers", embeddedKafka.getBrokersAsString());
//
////        kafkaRule.getEmbeddedKafka().
//    }



    @DisplayName("VALID TESTS")
    @Nested
    class EventToJsonConverter_ValidTests {
/*

        @Test
        @DisplayName("valid test")
        void valid() throws Exception {

            //Given
            RemList list = new RemList(new Id(UUID.randomUUID()), new RemListName("frutas"));
            NewRemListWasCreated created = new NewRemListWasCreated(list, hostName);
            created.addMetaAttribute("meta2", "valor 21");

            //When
            Map<String, Object> consumerProps = KafkaTestUtils.consumerProps("remlist-list-test", "true", embeddedKafka);
            consumerProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

            ConsumerFactory<String, String> cf = new DefaultKafkaConsumerFactory<>(consumerProps);
            Consumer<String, String> consumer = cf.createConsumer();

            ContainerProperties containerProperties = new ContainerProperties("pruebatopic");
            KafkaMessageListenerContainer<String, String> container =
                    new KafkaMessageListenerContainer(cf, containerProperties);


            final BlockingQueue<ConsumerRecord<String, String>> records = new LinkedBlockingQueue<>();
            container.setupMessageListener(new MessageListener<String, String>() {

                @Override
                public void onMessage(ConsumerRecord<String, String> record) {
                    System.out.println("### Record -->" + record);
                    records.add(record);
                }

            });


            container.start(); //Arranca el embeddedKafka
            ContainerTestUtils.waitForAssignment(container, embeddedKafka.getPartitionsPerTopic());

//            Map<String, Object> senderProps = KafkaTestUtils.senderProps(embeddedKafka.getBrokersAsString());
//            ProducerFactory<String, String> pf = new DefaultKafkaProducerFactory<>(senderProps);
//
//            KafkaTemplate<String, String> kafkaTemplate = new KafkaTemplate<>(pf);
//            kafkaTemplate.setDefaultTopic("pruebatopic");
//            String jsonEvent = eventEntityToJsonEventConverter.convertToJsonEvent(created);
//            kafkaTemplate.sendDefault(jsonEvent);
//            kafkaTemplate.sendDefault(0, "key1", "bar");

            publisher.publish(created);





            ConsumerRecord<String, String> received = records.poll(10L, TimeUnit.SECONDS);

            System.out.println("### received 1 --> " + received);


//            kafkaTemplate.send("pruebatopic", 0, UUID.randomUUID().toString(), "baz");

            received = records.poll(10L, TimeUnit.SECONDS);

            System.out.println("### received 2 --> " + received);




//            embeddedKafka.addTopics(new NewTopic( "topic_alvuelo", 10, (short)1) );

//            kafkaTemplate.send("pruebatopic", jsonEvent);

            System.out.println("### -->" + embeddedKafka.getTopics());

            //Then
//            assertThat();


        }
*/

    }

    @DisplayName("FAILED TESTS")
    @Nested
    class EventToJsonConverter_FailTests {

        @Test
        @DisplayName("fail test")
        void fail() {

            //Given

            //When

            //Then
//            assertThat();
        }
    }

}
