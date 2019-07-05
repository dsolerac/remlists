package com.remlists.user.read.infrastructure.spring.actuator;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Endpoint(id="userReadModuleHealth")
@Component
public class UserReadModuleHealth {

    @ReadOperation
    @Bean
    public String hi() {
        return "Hi from custom endpoint";
    }


}
