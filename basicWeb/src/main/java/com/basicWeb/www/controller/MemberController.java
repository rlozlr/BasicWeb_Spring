package com.basicWeb.www.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.basicWeb.www.security.MemberVO;
import com.basicWeb.www.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/member/*")
public class MemberController {

	private final MemberService msv;
	private final BCryptPasswordEncoder bcEncoder;
	
	@GetMapping("/register")
	public void register() {}
	
	@PostMapping("/register")
	public String register(MemberVO mvo) {
		mvo.setPwd(bcEncoder.encode(mvo.getPwd()));
		int isOk = msv.register(mvo);
		return "index";
	}
	
	@GetMapping("/login")
	public void login() {}
	
	@PostMapping("/login")
	public String login(HttpServletRequest request, RedirectAttributes re) {
		re.addAttribute("email", request.getAttribute("email"));
		re.addAttribute("errMsg", request.getAttribute("errMsg"));
		return "redirect:/member/login";
	}
}
