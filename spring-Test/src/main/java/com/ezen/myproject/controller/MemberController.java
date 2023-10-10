package com.ezen.myproject.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ezen.myproject.domain.MemberVO;
import com.ezen.myproject.service.MemberService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RequestMapping("/member/*")
@Controller
public class MemberController {

	private MemberService msv;

	
	@Autowired
	public MemberController(MemberService msv) {
		this.msv = msv;
	}



	@GetMapping("/signup")
	public String signupGet() {
		return "/member/signup";
	}
	
	
	@PostMapping("/signup")
	public String signupPost(MemberVO mvo) {
		log.info("mvo = {}", mvo);
		int isOk = msv.signup(mvo);
		log.info("signup = {}", (isOk > 0 ? "Ok" : "Fail"));
		return "index";
	}
	
	@GetMapping("/login")
	public String loginGet() {
		return "/member/login";
	}
	

	@PostMapping("/login")
	public String loginPost(MemberVO mvo, HttpServletRequest request, Model model) {
		log.info("login mvo = {}", mvo);
		// mvo 객체를 DB에서 일치하는지 체크
		MemberVO loginmvo = msv.isUser(mvo);
		
		//DB에서 가져온 loginmov != null 이면 세션에 저장
		if(loginmvo != null) {
			HttpSession ses = request.getSession();
			ses.setAttribute("ses", loginmvo); //세션에 로그인 객체 저장
			ses.setMaxInactiveInterval(600);
		}else {
			model.addAttribute("msg_login", 1);
		}
		return "index";
	}

	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request, Model model) {
		request.getSession().removeAttribute("ses");//세션 객체 삭제
		request.getSession().invalidate();// 세션끊기
		model.addAttribute("msg_logout", 1);
		
		return "index";
	}
	
	
	@GetMapping("/modify")
	public String modifyGet(){
		return "/member/modify";
	}
	
	
	@PostMapping("/modify")
	public String modifyPost(MemberVO mvo, RedirectAttributes rttr) {
		log.info("modify mvo = {}", mvo);
		int isOk = msv.modify(mvo);
		log.info("modify = {}", (isOk > 0 ? "Ok" : "Fail"));
		rttr.addFlashAttribute("msg_modify", 2);
		return "redirect:/member/logout";
	}
	
}
