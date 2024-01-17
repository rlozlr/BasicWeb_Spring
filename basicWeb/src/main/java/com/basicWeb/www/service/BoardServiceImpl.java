package com.basicWeb.www.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.basicWeb.www.domain.BoardVO;
import com.basicWeb.www.domain.PagingVO;
import com.basicWeb.www.repository.BoardDAO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService{

	private final BoardDAO bdao;

	@Override
	public void register(BoardVO bvo) {
		// TODO Auto-generated method stub
		bdao.register(bvo);
		
	}

	@Override
	public List<BoardVO> getList(PagingVO pgvo) {
		return bdao.getList(pgvo);
	}

	@Override
	public BoardVO getDetail(long bno) {
		bdao.upReadCount(bno, 1);
		return bdao.getDetail(bno);
	}

	@Override
	public void modify(BoardVO bvo) {
		bdao.upReadCount(bvo.getBno(), -2);
		bdao.update(bvo);
		
	}

	@Override
	public void remove(BoardVO bvo) {
		bdao.delete(bvo);
		
	}

	@Override
	public int totalCount() {
		// TODO Auto-generated method stub
		return bdao.totalCount();
	}
}
