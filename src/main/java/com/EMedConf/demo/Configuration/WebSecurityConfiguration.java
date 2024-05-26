package com.EMedConf.demo.Configuration;

import com.EMedConf.demo.Service.Implementation.LogoutService;
import com.EMedConf.demo.Utility.Role;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@AllArgsConstructor
@EnableWebMvc

public class WebSecurityConfiguration implements WebMvcConfigurer {

    @Autowired
    LogoutService logoutService;

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http)
            throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/").permitAll()
                                .requestMatchers(HttpMethod.POST,"/register/patient").permitAll()
                                .requestMatchers(HttpMethod.POST,"/register/medecin").permitAll()
                                .requestMatchers(HttpMethod.POST,"/home").hasAuthority(Role.MEDECIN.name())
                                .requestMatchers(HttpMethod.POST,"/admin").hasAuthority(Role.ADMIN.name())
                                .requestMatchers(HttpMethod.POST,"/patient").hasAuthority(Role.PATIENT.name())
                                .anyRequest().authenticated()
                                )
                .logout((logout) -> logout
                        .logoutUrl("/logout")
                        .addLogoutHandler(logoutService)
                        .logoutSuccessHandler(
                                (request, response, authentication) -> {
                                    SecurityContextHolder.clearContext();
                                })
                        .logoutSuccessUrl("/")
                        .permitAll())
                .httpBasic(withDefaults())
               ;



        return http.build();

    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration)
            throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:8080")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*");
    }

}
