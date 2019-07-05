package com.remlists.entrypoint.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class RemlistsEntrypointSecurityConfig extends WebSecurityConfigurerAdapter {

    private Logger LOG = LoggerFactory.getLogger(RemlistsEntrypointSecurityConfig.class);


    @Autowired
    private UserDetailsService userDetailsService;

    @ConditionalOnMissingBean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {


        http.authorizeRequests()
                .antMatchers("/**")
                .permitAll()
//                    .access("hasRole('USER')")
                .and()
                    .formLogin()
                .and()
                    .httpBasic()
                .and()
                    .logout()
                .and()
                    .csrf().disable();


/*
        http.
                sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .cors()
                .and()
                    .csrf().disable()
//                    .ignoringAntMatchers("/api/*")
//                .and()
                    .authorizeRequests()
                    .antMatchers("/css/*", "/imgs/*").permitAll()
                    .antMatchers("/login", "/v2/api-docs", "/actuator").permitAll()
                    .anyRequest().authenticated(); //Any other URL's need to be authenticated

//                .and()
//                .addFilter(new JWTAuthenticationFilter(authenticationManager(), SECRET, EXPIRATION_TIME))
//                .addFilter(new JWTAuthorizationFilter(authenticationManager(), SECRET, EXPIRATION_TIME))

*/



    }




    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean("corsConfigurationSource")
    public CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }


}
