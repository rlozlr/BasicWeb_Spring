package com.basicWeb.www.repository;

import java.util.List;

import com.basicWeb.www.security.AuthVO;
import com.basicWeb.www.security.MemberVO;

public interface MemberDAO {

	int insert(MemberVO mvo);

	int insertAuthInit(String email);

	MemberVO selectEmail(String username);

	List<AuthVO> selectAuths(String username);

	int updateLastLogin(String authEmail);

}
