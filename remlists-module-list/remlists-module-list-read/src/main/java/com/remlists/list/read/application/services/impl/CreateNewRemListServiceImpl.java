package com.remlists.list.read.application.services.impl;

import com.remlists.list.domain.entities.RemList;
import com.remlists.list.domain.repository.RemListRepository;
import com.remlists.list.domain.valueObjects.RemListName;
import com.remlists.list.read.application.services.CreateNewRemListService;
import com.remlists.shared.domain.valueObjects.Id;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.remlists.list.read.infrastructure.spring.BeanNames.Application.Service.CreateNewRemListService;
import static com.remlists.list.read.infrastructure.spring.BeanNames.Infrastructure.Spring.Repository.listReadCommandRepositoryJPA;
import static com.remlists.list.read.infrastructure.spring.BeanNames.Infrastructure.Spring.transactionManagerListRead;

@Service(CreateNewRemListService)
@Transactional(transactionManagerListRead)
public class CreateNewRemListServiceImpl implements CreateNewRemListService {

    private Logger LOG = LoggerFactory.getLogger(CreateNewRemListServiceImpl.class);

    private RemListRepository remListRepository;


    public CreateNewRemListServiceImpl( @Qualifier(listReadCommandRepositoryJPA) RemListRepository remListRepository){
        this.remListRepository = remListRepository;
    }


    @Override
    public void createNewRemList(Id id, RemListName name) {

        RemList remList = new RemList(id, name);

        remListRepository.save(remList);

    }




}
