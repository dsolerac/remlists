package com.remlists.user.write.infrastructure.spring.springData.impl;

import com.remlists.shared.infrastructure.jpa.valueObjects.IdJPA;
import com.remlists.user.write.infrastructure.jpa.entities.RoleJPA;
import com.remlists.user.write.infrastructure.jpa.repository.RoleRepositoryJPA;
import com.remlists.user.write.infrastructure.jpa.valueObjects.RoleNameJPA;
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
                                               implements RoleRepositoryJPA {


    private static final Logger LOG = LoggerFactory.getLogger(UserWriteDataCustomRepositoryImpl.class);


    private RoleRepositoryJPA repository;
    private final EntityManager em;


    public RoleWriteDataCustomRepositoryImpl(@Qualifier(entityManagerUserWrite) EntityManager em,
                                             @Qualifier(roleWriteDataRepository) RoleRepositoryJPA repository
    ) {

        super(RoleJPA.class, em);
        this.em = em;
        this.repository = repository;

    }




    @Override
    public Optional<RoleJPA> findByRoleName(RoleNameJPA roleName) {
        return repository.findByRoleName(roleName);

    }

    @Override
    public Set<RoleJPA> findByRoleNameIn(RoleNameJPA... rolesName) {
        return repository.findByRoleNameIn(rolesName);

    }

}
