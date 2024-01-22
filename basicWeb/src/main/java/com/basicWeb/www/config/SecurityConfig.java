package com.basicWeb.www.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.basicWeb.www.security.CustomAuthMemberService;
import com.basicWeb.www.security.LoginFailureHandler;
import com.basicWeb.www.security.LoginSuccessHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Bean
	public PasswordEncoder bcPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationSuccessHandler authSuccessHandler() {
		return new LoginSuccessHandler();
	}
	
	@Bean
	public AuthenticationFailureHandler authFailureHandler() {
		return new LoginFailureHandler();
	}

	@Bean
	protected UserDetailsService customUserService() {
		// TODO Auto-generated method stub
		return new CustomAuthMemberService();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// 인증되는 객체 설정
		auth.userDetailsService(customUserService())
		.passwordEncoder(bcPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// 화면에서 설정되는 권한에 따른 주소 맵핑 설정
		// csrf() 공격에 대한 설정 막기
		http.csrf().disable();

		// 승인 요청
		// antMatchers : 접근을 허용하는 값(경로)
		// antMatchers("/member/list", "/결제요청", "/상품요청..")
		// permitAll() : 누구나 접근 가능한 경로
		// authenticated() : 인증된 사용자만 가능
		http.authorizeRequests()
		.antMatchers("/member/list").hasRole("ADMIN")
		.antMatchers("/", "/board/list", "/board/detail", "/comment/**", "/upload/**", "/resources/**", "/member/register", "/member/login").permitAll()
		.anyRequest().authenticated();

		// 커스텀 로그인 페이지를 구성
		// Controller에 주소요청 맵ㅁ핑이 같이 있어야 함. (필수)
		http.formLogin()
		.usernameParameter("email")
		.passwordParameter("pwd")
		.loginPage("/member/login")
		.successHandler(authSuccessHandler())
		.failureHandler(authFailureHandler());

		// 로그아웃 페이지
		// 반드시 method = "post"
		http.logout()
		.logoutUrl("/member/logout")
		.invalidateHttpSession(true)
		.deleteCookies("JSESSIONID")
		.logoutSuccessUrl("/");
	}

	
	
	
}
