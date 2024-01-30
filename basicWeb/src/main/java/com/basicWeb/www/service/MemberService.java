package com.basicWeb.www.service;

import com.basicWeb.www.security.MemberVO;

public interface MemberService {

	int register(MemberVO mvo);

	boolean updateLastLogin(String authEmail);

}
