package com.remlists.user.write.infrastructure.repository.impl;

import com.remlists.shared.domain.valueObjects.Id;
import com.remlists.shared.domain.valueObjects.ValueObject;
import com.remlists.shared.infrastructure.repository.impl.RemListBaseRepository;
import com.remlists.user.domain.entities.User;
import com.remlists.user.domain.repository.UserRepository;
import com.remlists.user.domain.valueObjects.EmailAddress;
import com.remlists.user.domain.valueObjects.ShortName;
import com.remlists.user.write.infrastructure.jpa.entities.UserJPA;
import com.remlists.user.write.infrastructure.jpa.repository.UserRepositoryJPA;
import com.remlists.user.write.infrastructure.jpa.valueObjects.EmailAddressJPA;
import com.remlists.shared.infrastructure.jpa.valueObjects.IdJPA;
import com.remlists.user.write.infrastructure.jpa.valueObjects.ShortNameJPA;
import com.remlists.user.write.infrastructure.mapper.UserMapper;
import com.remlists.user.write.infrastructure.mapper.UserMapperStruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.remlists.user.write.infrastructure.spring.BeanNames.Infrastructure.Spring.Component.Mapper.userMapperWrite;
import static com.remlists.user.write.infrastructure.spring.BeanNames.Infrastructure.Spring.Repository.userWriteDataCustomRepositoryImpl;
import static com.remlists.user.write.infrastructure.spring.BeanNames.Infrastructure.Spring.Repository.userWriteRepository;
import static com.remlists.user.write.infrastructure.spring.BeanNames.Infrastructure.Spring.transactionManagerUserWrite;


@Repository(userWriteRepository)
@Transactional(transactionManagerUserWrite)
public class UserWriteRepositoryImpl extends RemListBaseRepository<User,
                                                             Id,
                                                             UserJPA,
                                                             IdJPA>
                                        implements UserRepository {

    private Logger LOG = LoggerFactory.getLogger(UserWriteRepositoryImpl.class);


    private UserRepositoryJPA repository;
    private UserMapper mapper;

    private UserMapperStruct mstruct;

    public UserWriteRepositoryImpl(@Qualifier(userWriteDataCustomRepositoryImpl) UserRepositoryJPA repository,
                                   @Qualifier(userMapperWrite) UserMapper mapper) {

        super( repository, mapper );

        this.repository = repository;
        this.mapper = mapper;

    }



/*
    @Override
    public <VO extends ValueObject> Optional<User> findByShortName(VO username) {

        ShortNameJPA shortNameJPA = mapper.getMapper().map(username, ShortNameJPA.class);

        Optional<UserJPA> byShortName = repository.<ShortNameJPA>findByShortName(shortNameJPA);


        if(byShortName.isEmpty())
            return Optional.empty();

        return  Optional.of( mapper.getMapper().map(byShortName.orElseThrow(), User.class) );

    }

    @Override
    public <VO extends ValueObject> Optional<User> findByEmail(VO email) {

        EmailAddressJPA emailAddressJPA = mapper.getMapper().map(email, EmailAddressJPA.class);

        Optional<UserJPA> byEmail = repository.<EmailAddressJPA>findByEmail(emailAddressJPA);

        if(byEmail.isEmpty())
            return Optional.<User>empty();

        return  Optional.of( mapper.getMapper().map(byEmail.get(), User.class) );

    }

    @Override
    public <VO extends ValueObject> Optional<User> findByEmailOrShortName(VO email, VO username) {

        EmailAddressJPA emailAddressJPA = mapper.getMapper().map(email, EmailAddressJPA.class);
        ShortNameJPA    shortNameJPA    = mapper.getMapper().map(username, ShortNameJPA.class);

        Optional<UserJPA> userJPA = repository.findByEmailOrShortName(emailAddressJPA, shortNameJPA);

        if(userJPA.isEmpty())
            return Optional.empty();

        return  Optional.of( mapper.getMapper().map(userJPA.orElseThrow(), User.class) );

    }
*/





    @Override
    public Optional<User> findByShortName(ShortName username) {


        ShortNameJPA shortNameJPA = mstruct.INSTANCE.ShortNameToShortNameJPA(username);

        Optional<UserJPA> byShortName = repository.<ShortNameJPA>findByShortName(shortNameJPA);


        if(byShortName.isEmpty())
            return Optional.empty();

        return  Optional.of( mapper.getMapper().map(byShortName.orElseThrow(), User.class) );
//        return  Optional.of( mstruct.INSTANCE. );
    }

    @Override
    public Optional<User> findByEmail(EmailAddress email) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findByEmailOrShortName(EmailAddress email, ShortName username) {
        return Optional.empty();
    }
}
