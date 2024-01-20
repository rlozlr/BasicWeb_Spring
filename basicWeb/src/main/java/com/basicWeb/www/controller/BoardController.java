package com.basicWeb.www.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.basicWeb.www.domain.BoardVO;
import com.basicWeb.www.domain.FileVO;
import com.basicWeb.www.domain.PagingVO;
import com.basicWeb.www.handler.PagingHandler;
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
	public void list (Model m, PagingVO pgvo) {
		log.info(">>> pgvo >>> {}", pgvo);
		List<BoardVO> list = bsv.getList(pgvo);
		int totalCount = bsv.totalCount(pgvo);
		PagingHandler ph = new PagingHandler(pgvo, totalCount);
		m.addAttribute("list", list);
		m.addAttribute("ph", ph);
	}
	
	@GetMapping({"/detail", "/modify"})
	public void detail (Model m, @RequestParam("bno") long bno) {
		m.addAttribute("bvo", bsv.getDetail(bno));
	}
	
	@PostMapping("/modify")
	public String modify (BoardVO bvo, RedirectAttributes re) {
		bsv.modify(bvo);
		re.addAttribute("bno", bvo.getBno());
		return "redirect:/board/detail?bno=" +bvo.getBno();
	}
	
	@GetMapping("/remove")
	public String remove (BoardVO bvo) {
		bsv.remove(bvo);
		return "redirect:/board/list";
	}
	
	
}
