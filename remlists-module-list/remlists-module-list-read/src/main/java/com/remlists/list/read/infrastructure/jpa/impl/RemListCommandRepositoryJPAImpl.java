package com.remlists.list.read.infrastructure.jpa.impl;

import com.remlists.list.domain.entities.RemList;
import com.remlists.list.domain.repository.RemListRepository;
import com.remlists.list.read.infrastructure.jpa.entities.RemListJPA;
import com.remlists.list.read.infrastructure.jpa.valueObjects.IdJPA;
import com.remlists.list.read.infrastructure.jpa.valueObjects.RemListNameJPA;
import com.remlists.list.read.infrastructure.mapper.RemListMapper;
import com.remlists.list.read.infrastructure.spring.springData.RemListCommandDataRepository;
import com.remlists.shared.domain.valueObjects.Id;
import com.remlists.shared.domain.valueObjects.ValueObject;
import com.remlists.shared.infrastructure.jpa.impl.BaseRepositoryJPA;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.remlists.list.read.infrastructure.spring.BeanNames.Infrastructure.Spring.Repository.listReadCommandRepositoryJPA;
import static com.remlists.list.read.infrastructure.spring.BeanNames.Infrastructure.Spring.Repository.remListCommandDataCustomRepositoryImpl;
import static com.remlists.list.read.infrastructure.spring.BeanNames.Infrastructure.Spring.transactionManagerListRead;
import static com.remlists.list.read.infrastructure.spring.BeanNames.Infrastructure.Spring.Component.Mapper.remListMapperRead;


@Repository(listReadCommandRepositoryJPA)
@Transactional(transactionManagerListRead)
public class RemListCommandRepositoryJPAImpl extends BaseRepositoryJPA< RemList,
                                                                      Id,
                                                                      RemListJPA,
                                                                      IdJPA>
                                           implements RemListRepository<RemList, Id> {

    private static final Logger LOG = LoggerFactory.getLogger(RemListCommandRepositoryJPAImpl.class);


    private RemListCommandDataRepository repository;
    private RemListMapper mapper;

    public RemListCommandRepositoryJPAImpl(@Qualifier(remListCommandDataCustomRepositoryImpl) RemListCommandDataRepository repository,
                                           @Qualifier(remListMapperRead) RemListMapper mapper){
        super( repository, mapper );

        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public <VO extends ValueObject> Optional<RemList> findByName(VO name) {

        RemListNameJPA nameJPA = mapper.getMapper().map(name, RemListNameJPA.class);

        Optional<RemListJPA> byName = repository.<RemListNameJPA>findByName(nameJPA);


        if(byName.isEmpty())
            return Optional.empty();

        return  Optional.of( mapper.getMapper().map(byName.orElseThrow(), RemList.class) );



    }
}
