package com.basicWeb.www.service;

import java.util.List;

import com.basicWeb.www.domain.BoardVO;
import com.basicWeb.www.domain.PagingVO;

public interface BoardService {

	void register(BoardVO bvo);

	List<BoardVO> getList(PagingVO pgvo);

	BoardVO getDetail(long bno);

	void modify(BoardVO bvo);

	void remove(BoardVO bvo);

	int totalCount();
	
}
