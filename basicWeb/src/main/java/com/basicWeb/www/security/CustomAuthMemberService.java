package com.basicWeb.www.security;

import javax.inject.Inject;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.basicWeb.www.repository.MemberDAO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class CustomAuthMemberService implements UserDetailsService {
	
	@Inject
	private MemberDAO mdao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MemberVO mvo = mdao.selectEmail(username);
		if(mvo == null) {
			throw new UsernameNotFoundException(username);
		}
		mvo.setAuthList(mdao.selectAuths(username));
		log.info(">>> userDetails >>> {}", mvo);
		return new AuthMember(mvo);
	}

}
