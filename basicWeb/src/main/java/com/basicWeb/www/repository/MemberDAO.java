package com.basicWeb.www.repository;

import com.basicWeb.www.security.MemberVO;

public interface MemberDAO {

	int insert(MemberVO mvo);

	int insertAuthInit(String email);

}
