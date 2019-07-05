package com.remlists.entrypoint.config;


import com.remlists.list.read.infrastructure.spring.config.RemlistsListReadModuleConfig;
import com.remlists.list.write.infrastructure.spring.config.RemlistsListWriteModuleConfig;
import com.remlists.shared.infrastructure.spring.config.RemlistsSharedModuleConfig;
import com.remlists.user.write.infrastructure.spring.config.RemlistsUserWriteModuleConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.*;
import org.springframework.hateoas.UriTemplate;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.hal.CurieProvider;
import org.springframework.hateoas.hal.DefaultCurieProvider;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.StringVendorExtension;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.*;

@Configuration
@EnableWebMvc
@Import({
        RemlistsSharedModuleConfig.class
        , RemlistsListReadModuleConfig.class
        , RemlistsListWriteModuleConfig.class
//        , RemlistsUserReadModuleConfig.class
        , RemlistsUserWriteModuleConfig.class
        , RemlistsEntrypointSecurityConfig.class

})
@ComponentScans({
        @ComponentScan("com.remlists.shared")
//        , @ComponentScan("com.remlists.user.read")
        , @ComponentScan("com.remlists.user.write")
        , @ComponentScan("com.remlists.list.read")
        , @ComponentScan("com.remlists.list.write")
})
@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
@EnableSwagger2
public class RemlistsEntrypointConfig {

    public static final Contact DEFAULT_CONTACT = new Contact(
            "Daniel Solera", "http://www.dsolerac.com", "dsolerac@gmail.com");

    public static final Collection<VendorExtension> VENDORS = List.of(new StringVendorExtension("VendorName", "VendorValue")) ;

    public static final ApiInfo DEFAULT_API_INFO = new ApiInfo(
            "Awesome API Title",
            "Awesome API Description",
            "1.0",
            "urn:tos",
             DEFAULT_CONTACT,
            "Apache 2.0",
            "http://www.apache.org/licenses/LICENSE-2.0",
            VENDORS);

    private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES =
            new HashSet<>(Arrays.asList("application/json",
                                        "application/xml"));




    @Bean
    public CurieProvider curieProvider() {
        return new DefaultCurieProvider("ex", new UriTemplate("http://www.example.com/rels/{rel}"));
    }

    @Bean
    public LocaleResolver localeResolver() {
        AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
        localeResolver.setDefaultLocale(Locale.US);
        return localeResolver;
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(DEFAULT_API_INFO)
                .produces(DEFAULT_PRODUCES_AND_CONSUMES)
                .consumes(DEFAULT_PRODUCES_AND_CONSUMES)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }



//Hay un problema al resolver /v2/api-docs que debido a que se encuentra el jar de jackson-dataformat-xml en el
//classpath muestra el resultado siempre en xml en vez de en JSON, de este modo se soluciona.
    @Bean
    public RequestMappingHandlerAdapter requestHandler() {
        RequestMappingHandlerAdapter adapter = new RequestMappingHandlerAdapter();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        List<MediaType> mediaTypeList = new ArrayList<>();
        mediaTypeList.add(MediaType.APPLICATION_JSON);
        converter.setSupportedMediaTypes(mediaTypeList);
        adapter.getMessageConverters().add(converter);
        return adapter;
    }




}
