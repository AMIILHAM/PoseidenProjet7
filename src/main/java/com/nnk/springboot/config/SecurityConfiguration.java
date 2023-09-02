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
        // configurela manière dont les requêtes HTTP doivent être autorisées dans votre application.
                // autorise l'accés à la pages de connexion (avec URL "/login") où les utilisateurs doivent saisir leurs informations d'identification.
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/login").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .permitAll()
                )
                //Cette partie configure la façon dont le processus de déconnexion doit être géré.
                .logout(LogoutConfigurer::permitAll)
                //démarre la configuration pour la gestion des exceptions.
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
