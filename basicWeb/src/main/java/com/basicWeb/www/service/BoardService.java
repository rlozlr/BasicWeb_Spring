package com.basicWeb.www.service;

import java.util.List;

import com.basicWeb.www.domain.BoardDTO;
import com.basicWeb.www.domain.BoardVO;
import com.basicWeb.www.domain.PagingVO;

public interface BoardService {

	int register(BoardDTO bdto);

	List<BoardVO> getList(PagingVO pgvo);

	BoardDTO getDetail(long bno);

	int modify(BoardDTO bdto);

	void remove(BoardVO bvo);

	int totalCount(PagingVO pgvo);

	int removeFile(String uuid);
	
}
