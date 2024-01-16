package com.basicWeb.www.service;

import org.springframework.stereotype.Service;

import com.basicWeb.www.domain.BoardVO;
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
}
