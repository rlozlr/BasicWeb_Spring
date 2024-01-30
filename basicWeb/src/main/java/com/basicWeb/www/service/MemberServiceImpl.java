package com.basicWeb.www.service;

import org.springframework.stereotype.Service;

import com.basicWeb.www.repository.MemberDAO;
import com.basicWeb.www.security.MemberVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

	private final MemberDAO mdao;

	@Override
	public int register(MemberVO mvo) {
		int isOk = mdao.insert(mvo);
		return mdao.insertAuthInit(mvo.getEmail());
	}

	@Override
	public boolean updateLastLogin(String authEmail) {
		// TODO Auto-generated method stub
		return mdao.updateLastLogin(authEmail) > 0 ? true : false;
	}
}
