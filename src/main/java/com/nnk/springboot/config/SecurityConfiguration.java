package com.nnk.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    /**
     *
     * @param http
     * @return
     * @throws Exception
     * <p>c'est une methode qu'on a redefinis pour personnaliser les resources qui doivent etre public sans authentification
     *    et aussi les resources qui doivent etre privee ou bien securisé </p>
     */



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(requests -> requests
                        //Permit access to all for the login and main home page
                        .requestMatchers("/login").permitAll()
                        //Authentication request parameters
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .permitAll()
                )
                //Configuration of the logout
                .logout(LogoutConfigurer::permitAll)
                //Definition of the default URL in case of exception of an access denied
                .exceptionHandling(exceptionHandling ->
                        exceptionHandling
                                .accessDeniedHandler(accessDeniedHandler())
                );

        return http.build();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }
}
