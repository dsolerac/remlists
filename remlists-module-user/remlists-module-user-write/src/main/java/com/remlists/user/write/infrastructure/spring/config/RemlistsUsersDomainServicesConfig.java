package com.remlists.user.write.infrastructure.spring.config;

import com.remlists.user.domain.repository.RoleRepository;
import com.remlists.user.domain.repository.UserRepository;
import com.remlists.user.domain.services.CreateBasicRemlistsUserDomainService;
import com.remlists.user.write.infrastructure.spring.security.RemlistUserPasswordEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.remlists.user.write.infrastructure.spring.BeanNames.Domain.Service.createUserDomainService;
import static com.remlists.user.write.infrastructure.spring.BeanNames.Infrastructure.Spring.Repository.roleWriteRepository;
import static com.remlists.user.write.infrastructure.spring.BeanNames.Infrastructure.Spring.Repository.userWriteRepository;

@Configuration
public class RemlistsUsersDomainServicesConfig {

    private Logger LOG = LoggerFactory.getLogger(RemlistsUsersDomainServicesConfig.class);


    @Qualifier(userWriteRepository)
    @Autowired
    private UserRepository userRepository;

    @Qualifier(roleWriteRepository)
    @Autowired
    private RoleRepository roleRepository;



    @Autowired
    private RemlistUserPasswordEncoder remlistUserPasswordEncoder;


    class Users {

        @Bean(createUserDomainService)
        public CreateBasicRemlistsUserDomainService createRemlistUserDomainService() {
            CreateBasicRemlistsUserDomainService domainService = new CreateBasicRemlistsUserDomainService(userRepository,
                                                                                                roleRepository,
                                                                                                remlistUserPasswordEncoder
                                                                                                );
            return domainService;
        }

    }

}
