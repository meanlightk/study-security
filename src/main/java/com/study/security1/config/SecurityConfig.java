package com.study.security1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity  // 스프링 시큐리티 필터(SecurityConfig)가 스프링 필터체인에 등록이 된다.
public class SecurityConfig {

    // 해당 메소드의 리턴되는 오브젝트를 ioc로 등록해준다.

    @Bean
    public BCryptPasswordEncoder encoderPwd() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf(CsrfConfigurer::disable);
        http.authorizeHttpRequests(authorize ->
                authorize
                        .requestMatchers("/user/**").authenticated()
                        .requestMatchers("/manager/**").hasAnyRole("ADMIN", "MANAGER")
                        .requestMatchers("/admin/**").hasAnyRole("ADMIN")
                        .anyRequest().permitAll()
        );
        /**
         * 로그인이 되어있지 않으면 어떤 페이지로 이동하든 로그인 페이지로 이동
         */
        http.formLogin(f -> f.loginPage("/loginForm")
                        .loginProcessingUrl("/loginForm")
                        .usernameParameter("userName")
                        .passwordParameter("/password")
                        .defaultSuccessUrl("/")
                        .permitAll())
                .httpBasic(h -> h.disable());

        // 로그아웃
//        http.logout(logout -> logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                .logoutSuccessUrl("/loginForm"));


        return http.build();


    }
}
