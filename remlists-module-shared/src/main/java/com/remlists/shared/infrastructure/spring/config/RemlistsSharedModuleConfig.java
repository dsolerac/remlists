package com.remlists.shared.infrastructure.spring.config;


import com.google.gson.Gson;
//import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RemlistsSharedModuleConfig {

    private Logger LOG = LoggerFactory.getLogger(RemlistsSharedModuleConfig.class);


//    @Bean
//    @ConditionalOnMissingBean
//    public ModelMapper modelMapper() {
//        return new ModelMapper();
//    }

    @Bean
    @ConditionalOnMissingBean
    public Gson jsonConverter(){

        return new Gson();
    }


}
