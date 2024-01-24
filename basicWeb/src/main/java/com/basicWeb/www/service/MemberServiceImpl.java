package com.basicWeb.www.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.basicWeb.www.repository.MemberDAO;
import com.basicWeb.www.security.MemberVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

	private final MemberDAO mdao;

	@Transactional
	@Override
	public int register(MemberVO mvo) {
		int isOk = mdao.insert(mvo);
		return mdao.insertAuthInit(mvo.getEmail());
	}
}
