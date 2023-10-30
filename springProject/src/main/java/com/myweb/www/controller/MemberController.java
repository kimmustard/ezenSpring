package com.myweb.www.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myweb.www.security.MemberVO;
import com.myweb.www.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/member/**")
@RequiredArgsConstructor
public class MemberController {

	
	private final MemberService msv;
	
	private final BCryptPasswordEncoder bcEncoder;
	
	@GetMapping("/register")
	public String register(@ModelAttribute("mvo")MemberVO mvo, Model model) {
		model.addAttribute("mvo", mvo);
		return "/member/register";
	}
	
	@PostMapping("/register")
	public String register(@Validated @ModelAttribute("mvo") MemberVO mvo, BindingResult bindingResult, Model model) {
		
		if(bindingResult.hasErrors()) {
			return "/member/register";
		}
		
		log.info("mvo ={}" , mvo);
		
		//암호화
		mvo.setPwd(bcEncoder.encode(mvo.getPwd()));
		
		int isOk = msv.register(mvo);
		
		return "index";
	}
	
	@GetMapping("/login")
	public String loginForm() {
		return "/member/login";
	}
	
	@PostMapping("/login")
	public String loginPost(HttpServletRequest request, RedirectAttributes re) {
		
		//로그인 실패시 다시 로그인페이지로 돌아와 오류 메시지 전송
		//다시 로그인 유도
		re.addAttribute("email", request.getAttribute("email"));
		re.addAttribute("errMsg", request.getAttribute("errMsg"));
		
		return "redirect:/member/login";
	}
	
	
	@GetMapping("/member/list")
	public String memberList(Model model) {
		List<MemberVO> list = msv.MemberList();
		
		model.addAttribute("list",list);
		return "/member/list";
	}
	

	
}
