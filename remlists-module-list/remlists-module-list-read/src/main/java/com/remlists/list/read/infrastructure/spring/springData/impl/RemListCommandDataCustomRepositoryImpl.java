package com.remlists.list.read.infrastructure.spring.springData.impl;


import com.remlists.list.domain.repository.RemListRepository;
import com.remlists.list.read.infrastructure.jpa.entities.RemListJPA;
import com.remlists.list.read.infrastructure.jpa.valueObjects.IdJPA;
import com.remlists.list.read.infrastructure.spring.springData.RemListCommandDataRepository;
import com.remlists.shared.domain.valueObjects.ValueObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Optional;

import static com.remlists.list.read.infrastructure.spring.BeanNames.Infrastructure.Spring.Repository.remListCommandDataCustomRepositoryImpl;
import static com.remlists.list.read.infrastructure.spring.BeanNames.Infrastructure.Spring.Repository.remListCommandDataRepository;
import static com.remlists.list.read.infrastructure.spring.BeanNames.Infrastructure.Spring.entityManagerListRead;
import static com.remlists.list.read.infrastructure.spring.BeanNames.Infrastructure.Spring.transactionManagerListRead;

@Repository(remListCommandDataCustomRepositoryImpl)
@Transactional(transactionManagerListRead)
public class RemListCommandDataCustomRepositoryImpl extends SimpleJpaRepository<RemListJPA, IdJPA>
                                              implements RemListCommandDataRepository {

    private static final Logger LOG = LoggerFactory.getLogger(RemListCommandDataCustomRepositoryImpl.class);


    private RemListRepository repository;
    private final EntityManager em;


    public RemListCommandDataCustomRepositoryImpl(@Qualifier(entityManagerListRead) EntityManager em,
                                                  @Qualifier(remListCommandDataRepository) RemListRepository repository

    ) {

        super(RemListJPA.class, em);
        this.em = em;
        this.repository = repository;
    }


    @Override
    public <VO extends ValueObject> Optional<RemListJPA> findByName(VO name) {
        return repository.findByName(name);
    }
}
