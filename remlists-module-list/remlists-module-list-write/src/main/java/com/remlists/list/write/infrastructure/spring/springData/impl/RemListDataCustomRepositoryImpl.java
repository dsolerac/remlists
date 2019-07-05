package com.remlists.list.write.infrastructure.spring.springData.impl;


import com.remlists.list.domain.repository.RemListRepository;
import com.remlists.list.write.infrastructure.jpa.entities.RemListJPA;
import com.remlists.list.write.infrastructure.jpa.valueObjects.IdJPA;
import com.remlists.list.write.infrastructure.spring.springData.RemListDataRepository;
import com.remlists.shared.domain.valueObjects.ValueObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Optional;

import static com.remlists.list.write.infrastructure.spring.BeanNames.Infrastructure.Spring.Repository.remListDataCustomRepositoryImpl;
import static com.remlists.list.write.infrastructure.spring.BeanNames.Infrastructure.Spring.Repository.remListDataRepository;
import static com.remlists.list.write.infrastructure.spring.BeanNames.Infrastructure.Spring.entityManagerListWrite;
import static com.remlists.list.write.infrastructure.spring.BeanNames.Infrastructure.Spring.transactionManagerListWrite;

@Repository(remListDataCustomRepositoryImpl)
@Transactional(transactionManagerListWrite)
public class RemListDataCustomRepositoryImpl extends SimpleJpaRepository<RemListJPA, IdJPA>
                                             implements RemListDataRepository {

    private static final Logger LOG = LoggerFactory.getLogger(RemListDataCustomRepositoryImpl.class);

    @Qualifier(remListDataRepository)
    @Autowired
    private RemListRepository repository;

    private final EntityManager em;


    public RemListDataCustomRepositoryImpl( @Qualifier(entityManagerListWrite) EntityManager em,
                                            @Qualifier(remListDataRepository) RemListDataRepository repository
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