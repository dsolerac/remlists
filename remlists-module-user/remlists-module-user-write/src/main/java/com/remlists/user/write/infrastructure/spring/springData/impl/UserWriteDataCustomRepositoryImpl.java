package com.remlists.user.write.infrastructure.spring.springData.impl;


import com.remlists.shared.domain.valueObjects.ValueObject;
import com.remlists.user.domain.repository.UserRepository;
import com.remlists.user.write.infrastructure.jpa.entities.UserJPA;
import com.remlists.shared.infrastructure.jpa.valueObjects.IdJPA;
import com.remlists.user.write.infrastructure.jpa.repository.UserRepositoryJPA;
import com.remlists.user.write.infrastructure.jpa.valueObjects.EmailAddressJPA;
import com.remlists.user.write.infrastructure.jpa.valueObjects.ShortNameJPA;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Optional;

import static com.remlists.user.write.infrastructure.spring.BeanNames.Infrastructure.Spring.Repository.userWriteDataCustomRepositoryImpl;
import static com.remlists.user.write.infrastructure.spring.BeanNames.Infrastructure.Spring.Repository.userWriteDataRepository;
import static com.remlists.user.write.infrastructure.spring.BeanNames.Infrastructure.Spring.entityManagerUserWrite;
import static com.remlists.user.write.infrastructure.spring.BeanNames.Infrastructure.Spring.transactionManagerUserWrite;


@Repository(userWriteDataCustomRepositoryImpl)
@Transactional(transactionManagerUserWrite)
public class UserWriteDataCustomRepositoryImpl
                                    extends SimpleJpaRepository<UserJPA, IdJPA>
                                    implements UserRepositoryJPA
{

    private static final Logger LOG = LoggerFactory.getLogger(UserWriteDataCustomRepositoryImpl.class);


    private UserRepositoryJPA repository;
    private final EntityManager em;


    public UserWriteDataCustomRepositoryImpl(@Qualifier(entityManagerUserWrite) EntityManager em,
                                             @Qualifier(userWriteDataRepository) UserRepositoryJPA repository
                                      ) {

        super(UserJPA.class, em);
        this.em = em;
        this.repository = repository;

    }


    @Override
    public Optional<UserJPA> findByShortName(ShortNameJPA username) {
        return repository.findByShortName(username);
    }

    @Override
    public Optional<UserJPA> findByEmail(EmailAddressJPA email) {
        return repository.findByEmail(email);
    }

    @Override
    public Optional<UserJPA> findByEmailOrShortName(EmailAddressJPA email, ShortNameJPA username) {
        return repository.findByEmailOrShortName(email, username);
    }
}