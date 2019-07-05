package com.remlists.list.read.infrastructure.spring.springData.impl;


import com.remlists.list.domain.repository.RemListRepository;
import com.remlists.list.read.infrastructure.jpa.entities.RemListJPA;
import com.remlists.list.read.infrastructure.jpa.valueObjects.IdJPA;
import com.remlists.list.read.infrastructure.spring.springData.RemListQueryDataRepository;
import com.remlists.shared.domain.valueObjects.ValueObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Optional;

import static com.remlists.list.read.infrastructure.spring.BeanNames.Infrastructure.Spring.Repository.remListQueryDataCustomRepositoryImpl;
import static com.remlists.list.read.infrastructure.spring.BeanNames.Infrastructure.Spring.Repository.remListQueryDataRepository;
import static com.remlists.list.read.infrastructure.spring.BeanNames.Infrastructure.Spring.entityManagerListRead;
import static com.remlists.list.read.infrastructure.spring.BeanNames.Infrastructure.Spring.transactionManagerListRead;

@Repository(remListQueryDataCustomRepositoryImpl)
@Transactional(transactionManagerListRead)
public class RemListQueryDataCustomRepositoryImpl extends SimpleJpaRepository<RemListJPA, IdJPA>
                                            implements RemListQueryDataRepository {

    private static final Logger LOG = LoggerFactory.getLogger(RemListQueryDataCustomRepositoryImpl.class);


    private RemListRepository repository;
    private final EntityManager em;


    public RemListQueryDataCustomRepositoryImpl(@Qualifier(entityManagerListRead) EntityManager em,
                                                @Qualifier(remListQueryDataRepository) RemListRepository repository) {

        super(RemListJPA.class, em);
        this.em = em;
        this.repository=repository;

    }


    @Override
    public <VO extends ValueObject> Optional<RemListJPA> findByName(VO name) {
        return repository.findByName(name);
    }
}