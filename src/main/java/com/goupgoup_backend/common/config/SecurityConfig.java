package com.goupgoup_backend.common.config;

import com.goupgoup_backend.user.config.OAuth2SuccessHandler;
import com.goupgoup_backend.user.service.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    private final CustomOAuth2UserService customOAuth2UserService;
    private final OAuth2SuccessHandler oAuth2SuccessHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
       http
               .cors()
               .and()
               .httpBasic().disable()
               .csrf().disable()
               .formLogin().disable()
               .rememberMe().disable();

       http.authorizeRequests()
               .antMatchers("/oauth2/**", "/swagger-ui.html", "/login/**").permitAll()
               .anyRequest().authenticated();

       http.oauth2Login()
               .authorizationEndpoint().baseUri("/oauth2/authorization")
               .and()
               .redirectionEndpoint().baseUri("/login/oauth2/code/*")
               .and()
               .userInfoEndpoint().userService(customOAuth2UserService)
               .and()
               .defaultSuccessUrl("http://localhost:3000")
               .successHandler(oAuth2SuccessHandler);

       http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

       return http.build();
    }
}
