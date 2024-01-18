package com.basicWeb.www.service;

import java.util.List;

import com.basicWeb.www.domain.CommentVO;

public interface CommentService{

	int post(CommentVO cvo);

	List<CommentVO> getList(long bno);

}
