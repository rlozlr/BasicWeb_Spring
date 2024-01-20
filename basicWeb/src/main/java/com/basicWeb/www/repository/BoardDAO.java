package com.basicWeb.www.repository;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.basicWeb.www.domain.BoardVO;
import com.basicWeb.www.domain.PagingVO;

public interface BoardDAO {

	int register(BoardVO bvo);

	List<BoardVO> getList(PagingVO pgvo);

	BoardVO getDetail(long bno);

	void update(BoardVO bvo);

	void delete(BoardVO bvo);

	void upReadCount(@Param("bno") long bno, @Param("count") int count);

	int totalCount(PagingVO pgvo);

	void getCmtCount();

	long selectOneBno();

}
