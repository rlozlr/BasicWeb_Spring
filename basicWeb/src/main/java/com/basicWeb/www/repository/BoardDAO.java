package com.basicWeb.www.repository;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.basicWeb.www.domain.BoardVO;

public interface BoardDAO {

	void register(BoardVO bvo);

	List<BoardVO> getList();

	BoardVO getDetail(long bno);

	void update(BoardVO bvo);

	void delete(BoardVO bvo);

	void upReadCount(@Param("bno") long bno, @Param("count") int count);

}
