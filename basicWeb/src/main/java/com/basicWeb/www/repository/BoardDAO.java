package com.basicWeb.www.repository;


import java.util.List;

import com.basicWeb.www.domain.BoardVO;

public interface BoardDAO {

	void register(BoardVO bvo);

	List<BoardVO> getList();

	BoardVO getDetail(long bno);

}
