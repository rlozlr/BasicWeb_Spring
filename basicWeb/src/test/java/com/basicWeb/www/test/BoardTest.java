package com.basicWeb.www.test;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.basicWeb.www.domain.BoardVO;
import com.basicWeb.www.repository.BoardDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {com.basicWeb.www.config.RootConfig.class})
public class BoardTest {

	@Inject
	private BoardDAO bdao;
	
	@Test
	public void insertBoard() {
		for(int i = 0; i < 300; i++) {
			BoardVO bvo = new BoardVO();
			bvo.setTitle("Title Test " + i);
			bvo.setWriter("tester");
			bvo.setContent(i + "번째 Test Content 입니다.");
			bdao.register(bvo);
		}
	}
}
