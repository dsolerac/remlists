package com.remlists.list.read.application.services.impl;

import com.remlists.list.domain.entities.RemList;
import com.remlists.list.domain.repository.RemListRepository;
import com.remlists.list.read.application.services.GetRemListService;
import com.remlists.shared.domain.valueObjects.Id;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.remlists.list.read.infrastructure.spring.BeanNames.Infrastructure.Spring.Repository.listReadQueryRepositoryJPA;
import static com.remlists.list.read.infrastructure.spring.BeanNames.Infrastructure.Spring.transactionManagerListRead;

@Service
@Transactional(transactionManagerListRead)
public class GetRemListServiceImpl implements GetRemListService {


    private Logger LOG = LoggerFactory.getLogger(CreateNewRemListServiceImpl.class);

    private RemListRepository remListRepository;


    public GetRemListServiceImpl( @Qualifier(listReadQueryRepositoryJPA) RemListRepository remListRepository){
        this.remListRepository = remListRepository;
    }


    @Override
    public Optional<RemList> getRemList(Id id) {

       return remListRepository.findById(id);

    }
}
