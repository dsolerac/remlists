package com.remlists.list.write.application.services.impl;

import com.remlists.list.domain.entities.RemList;
import com.remlists.list.domain.events.NewRemListWasCreated;
import com.remlists.list.domain.repository.RemListRepository;
import com.remlists.list.domain.valueObjects.RemListName;
import com.remlists.list.write.application.services.CreateNewRemListService;
import com.remlists.shared.domain.events.MessagePublisher;
import com.remlists.shared.domain.valueObjects.Id;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.remlists.list.write.infrastructure.spring.BeanNames.Application.Service.CreateNewRemListService;
import static com.remlists.list.write.infrastructure.spring.BeanNames.Infrastructure.Spring.Component.Publisher.Kafka.listWriteKafkaMessagePublisher;
import static com.remlists.list.write.infrastructure.spring.BeanNames.Infrastructure.Spring.Repository.listWriteRepositoryJPA;
import static com.remlists.list.write.infrastructure.spring.BeanNames.Infrastructure.Spring.transactionManagerListWrite;


@Service (CreateNewRemListService)
@Transactional(transactionManagerListWrite)
public class CreateNewRemListServiceImpl implements CreateNewRemListService {

    private Logger LOG = LoggerFactory.getLogger(CreateNewRemListServiceImpl.class);

    private MessagePublisher publisher;
    private RemListRepository remListRepository;

    private String hostName;


    public CreateNewRemListServiceImpl( @Qualifier(listWriteRepositoryJPA) RemListRepository remListRepository,
                                        @Qualifier(listWriteKafkaMessagePublisher) MessagePublisher publisher,
                                        @Value("${remlists.list.write.config.host.name}") String hostName
                                     ){
        this.publisher = publisher;
        this.remListRepository = remListRepository;
        this.hostName = hostName;
    }

    @Override
    public void createNewRemList(Id id, RemListName name) {

//        Steps:
//          1.- Se crear la lista.
//          2.- Se persiste la lista, si la creación ha ido bien.
//          3.- Se lanza el evento si ha ido bien.


        RemList remList = new RemList(id, name);

        remListRepository.save(remList);

        NewRemListWasCreated event = new NewRemListWasCreated(remList, hostName);
        publisher.publish( event );


    }

}
