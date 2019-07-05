package com.remlists.user.write.infrastructure.spring.config;

import com.remlists.user.domain.repository.RoleRepository;
import com.remlists.user.domain.repository.UserRepository;
import com.remlists.user.domain.services.CreateRemlistsUserDomainService;
import com.remlists.user.domain.services.PasswordEncoderDomainService;
import com.remlists.user.write.infrastructure.spring.security.RemlistUserPasswordEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.remlists.user.write.infrastructure.spring.BeanNames.Domain.Service.createUserDomainService;
import static com.remlists.user.write.infrastructure.spring.BeanNames.Domain.Service.passwordEncoderDomainService;
import static com.remlists.user.write.infrastructure.spring.BeanNames.Infrastructure.Spring.Repository.roleWriteRepositoryJPA;
import static com.remlists.user.write.infrastructure.spring.BeanNames.Infrastructure.Spring.Repository.userWriteRepositoryJPA;

@Configuration
public class RemlistsUsersDomainServicesConfig {

    private Logger LOG = LoggerFactory.getLogger(RemlistsUsersDomainServicesConfig.class);


    @Qualifier(userWriteRepositoryJPA)
    @Autowired
    private UserRepository userRepository;

    @Qualifier(roleWriteRepositoryJPA)
    @Autowired
    private RoleRepository roleRepository;



    @Autowired
    private RemlistUserPasswordEncoder remlistUserPasswordEncoder;


    class Users {

        @Bean(createUserDomainService)
        public CreateRemlistsUserDomainService createRemlistUserDomainService() {
            CreateRemlistsUserDomainService domainService = new CreateRemlistsUserDomainService(userRepository,
                                                                                                roleRepository,
                                                                                                remlistUserPasswordEncoder
                                                                                                );
            return domainService;
        }

    }

}
