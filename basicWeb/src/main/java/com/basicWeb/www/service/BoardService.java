package com.basicWeb.www.service;

import java.util.List;

import com.basicWeb.www.domain.BoardVO;

public interface BoardService {

	void register(BoardVO bvo);

	List<BoardVO> getList();

	BoardVO getDetail(long bno);

	void modify(BoardVO bvo);

	void remove(BoardVO bvo);
	
}
