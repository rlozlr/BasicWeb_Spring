package com.basicWeb.www.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.basicWeb.www.domain.CommentVO;
import com.basicWeb.www.domain.PagingVO;
import com.basicWeb.www.handler.PagingHandler;
import com.basicWeb.www.repository.CommentDAO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
	
	private final CommentDAO cdao;

	@Override
	public int post(CommentVO cvo) {
		// TODO Auto-generated method stub
		return cdao.insert(cvo);
	}

	@Override
	public PagingHandler getList(long bno, PagingVO pgvo) {
		List<CommentVO>list = cdao.getList(bno, pgvo);
		int cmtCount = cdao.totalCount(bno);
		PagingHandler ph = new PagingHandler(pgvo, cmtCount, list);
		return ph;
	}

}
