package com.basicWeb.www.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.basicWeb.www.domain.BoardVO;
import com.basicWeb.www.domain.CommentVO;
import com.basicWeb.www.domain.PagingVO;

public interface CommentDAO {

	int insert(CommentVO cvo);

	List<CommentVO> getList(@Param("bno") long bno, @Param("pgvo")PagingVO pgvo);

	int totalCount(long bno);

	int update(CommentVO cvo);

	void delete(long cno);

	void deleteAll(BoardVO bvo);

}
