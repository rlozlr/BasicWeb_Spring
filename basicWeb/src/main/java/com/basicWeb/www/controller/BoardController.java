package com.basicWeb.www.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.basicWeb.www.domain.BoardVO;
import com.basicWeb.www.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/board/*")
@RequiredArgsConstructor
@Controller
public class BoardController {

	private final BoardService bsv;
	
	@GetMapping("/register")
	public void register() {}
	
	@PostMapping("/register")
	public String register(BoardVO bvo) {
		bsv.register(bvo);
		return "index";
	}
	
	@GetMapping("/list")
	public void list (Model m) {
		List<BoardVO> list = bsv.getList();
		m.addAttribute("list", list);
	}
	
	@GetMapping("/detail")
	public void detail (Model m, @RequestParam("bno") long bno) {
		m.addAttribute("bvo", bsv.getDetail(bno));
	}
	
}