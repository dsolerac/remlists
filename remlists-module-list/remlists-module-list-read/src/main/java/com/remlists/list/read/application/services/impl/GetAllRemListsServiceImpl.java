package com.remlists.list.read.application.services.impl;

import com.remlists.list.domain.repository.RemListRepository;
import com.remlists.list.read.application.services.GetAllRemListsService;
import com.remlists.list.domain.entities.RemList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.remlists.list.read.infrastructure.spring.BeanNames.Infrastructure.Spring.Repository.listReadQueryRepositoryJPA;
import static com.remlists.list.read.infrastructure.spring.BeanNames.Infrastructure.Spring.transactionManagerListRead;

@Service
@Transactional(transactionManagerListRead)
public class GetAllRemListsServiceImpl implements GetAllRemListsService {


    private Logger LOG = LoggerFactory.getLogger(CreateNewRemListServiceImpl.class);

    private RemListRepository remListQueryRepository;


    public GetAllRemListsServiceImpl(@Qualifier(listReadQueryRepositoryJPA) RemListRepository remListQueryRepository) {
        this.remListQueryRepository = remListQueryRepository;
    }

    @Override
    public Iterable<RemList> getAllRemLists() {

        return remListQueryRepository.findAll();

    }
}
