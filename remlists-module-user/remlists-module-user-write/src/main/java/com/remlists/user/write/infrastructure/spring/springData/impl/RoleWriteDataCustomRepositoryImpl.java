package com.remlists.user.write.infrastructure.spring.springData.impl;

import com.remlists.shared.domain.valueObjects.ValueObject;
import com.remlists.shared.infrastructure.jpa.valueObjects.IdJPA;
import com.remlists.user.domain.repository.RoleRepository;
import com.remlists.user.write.infrastructure.jpa.entities.RoleJPA;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Optional;
import java.util.Set;

import static com.remlists.user.write.infrastructure.spring.BeanNames.Infrastructure.Spring.Repository.roleWriteDataCustomRepositoryImpl;
import static com.remlists.user.write.infrastructure.spring.BeanNames.Infrastructure.Spring.Repository.roleWriteDataRepository;
import static com.remlists.user.write.infrastructure.spring.BeanNames.Infrastructure.Spring.entityManagerUserWrite;
import static com.remlists.user.write.infrastructure.spring.BeanNames.Infrastructure.Spring.transactionManagerUserWrite;


@Repository(roleWriteDataCustomRepositoryImpl)
@Transactional(transactionManagerUserWrite)
public class RoleWriteDataCustomRepositoryImpl extends SimpleJpaRepository<RoleJPA, IdJPA>
                                               implements RoleRepository<RoleJPA, IdJPA>{


    private static final Logger LOG = LoggerFactory.getLogger(UserWriteDataCustomRepositoryImpl.class);


    private RoleRepository repository;
    private final EntityManager em;


    public RoleWriteDataCustomRepositoryImpl(@Qualifier(entityManagerUserWrite) EntityManager em,
                                             @Qualifier(roleWriteDataRepository) RoleRepository repository
    ) {

        super(RoleJPA.class, em);
        this.em = em;
        this.repository = repository;

    }



    @Override
    public <VO extends ValueObject> Optional<RoleJPA> findByRoleName(VO role) {
        return repository.findByRoleName(role);
    }

    @Override
    public <VO extends ValueObject> Set<RoleJPA> findByRoleNameIn(VO... roles) {
        return repository.findByRoleNameIn(roles);
    }

}
