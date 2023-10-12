package com.ezen.myproject.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ezen.myproject.domain.MemberVO;
import com.ezen.myproject.service.MemberService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RequestMapping("/member/*")
@Controller
public class MemberContoroller {

	private MemberService msv;
	
	@Autowired
	public MemberContoroller(MemberService msv) {
		this.msv = msv;
	}
	
	
	@GetMapping("/signup")
	public String signGet() {
		log.info("sign GET check");
		return "/member/signup";
	}
	
	@PostMapping("/signup")
	public String signPost(@ModelAttribute MemberVO mvo) {
		log.info("sign POST check");
		int isOk = msv.signup(mvo);
		return "index";
	}
	
	
	@GetMapping("/login")
	public String loginGet() {
		log.info("loginGET check");
		return "/member/login";
	}
	
	
	@PostMapping("/login")
	public String loginPost(@ModelAttribute MemberVO mvo, HttpServletRequest request, Model model) {
		//받아온 mvo가 DB내부 회원정보가 있는지 일치하는지 체크
		MemberVO loginmvo = msv.isUser(mvo);
		log.info("mvo = {}", loginmvo);
		
		//DB에서 가져온 loginmvo != null 이면 세션에 저장
		if(loginmvo != null){
			HttpSession ses = request.getSession();
			ses.setAttribute("ses", loginmvo);	//세션에 아이디정보 담아서 저장
			ses.setMaxInactiveInterval(600);	//로그인시간
		}else {
			model.addAttribute("msg_login", 1);
		}
		return "index";
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request, Model model) {
		request.getSession().removeAttribute("ses"); //세션 객체 삭제
		request.getSession().invalidate(); // 세션 로그아웃
		model.addAttribute("msg_logout", 1);	
		
		return "index";
	}
	
	@GetMapping("/modify")
	public String modifyGet() {
		return "/member/modify";
	}
	
	@PostMapping("/modify")
	public String modifyPost(MemberVO mvo, RedirectAttributes rttr) {
		log.info("modify mvo = {}" , mvo);
		int isOk = msv.modify(mvo);
		log.info("modify = {}", (isOk > 0 ? "Ok" : "Fail"));
		rttr.addFlashAttribute("msg_modify", 2);
		return "redirect:/member/logout";
	}
	
}
