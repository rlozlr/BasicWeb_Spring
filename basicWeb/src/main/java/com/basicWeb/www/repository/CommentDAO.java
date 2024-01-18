package com.basicWeb.www.repository;

import java.util.List;

import com.basicWeb.www.domain.CommentVO;

public interface CommentDAO {

	int insert(CommentVO cvo);

	List<CommentVO> getList(long bno);

}
