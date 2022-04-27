package com.jojoldu.book.springboot.config.auth;

import com.jojoldu.book.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity // SpringSecurity 설정들을 활성화 시킴
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // h2-console 화면을 사용하기 위해 해당 옵션들을 disable 한다.
                .csrf().disable()
                .headers().frameOptions().disable()

                .and()
                // URL 별 관릴를 설정하는 옵션의 시작점(antMatchers를 사용하기 위한 시작점)
                .authorizeRequests()
                // 관리 권한 대상을 지정하는 옵션
                .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()
                .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                .anyRequest().authenticated() // 그 외 모든 요청은 인증(로그인)되어야 한다.

                .and()
                // 로그아웃 기능에 대한 여러 설정의 진입점
                .logout()
                .logoutSuccessUrl("/")// 로그아웃 성공시 "/"주소로 이동

                .and()
                // OAuth2 로그인 기능에 대한 여러 설정의 진입점
                .oauth2Login()
                // OAuth2 로그인 성공 이후 사용자 정보를 가져올 때의 설정들을 담당
                .userInfoEndpoint()
                // 소셜 로그인 성공 시 후속 조치를 진행할 UserService 인터페이스의 구현체를 등록
                .userService(customOAuth2UserService);

    }
}
