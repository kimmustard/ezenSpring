package com.myweb.www.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.PagingVO;
import com.myweb.www.handler.PagingHandler;
import com.myweb.www.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/board/*")
//@PropertySource("classpath:test.properties")
@RequiredArgsConstructor
public class BoardController {
	
	//폴더명 : board / requestMapping : board
	//mapping => /board/register
	//목적지 => /board/register
	
	private final BoardService bsv;
	
	
//	@Value("${test}")
//	private String test;
	
	@GetMapping("/register")
	public String register(Model model) {
		log.info("log test");
//		System.out.println("test = " + test);
		model.addAttribute("bvo",new BoardVO());
		return "/board/register";
	}
	

	@PostMapping("/register")
	public String registerPost(@Validated @ModelAttribute("bvo") BoardVO bvo, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			return "/board/register";
		}
		
		log.info("register bvo ={} ", bvo);
		int isOk = bsv.insert(bvo); 
		log.info("register = {} ", (isOk > 0 ? "Ok" : "Fail"));
		
		return "redirect:/board/list";
	}
	
	
	//paging 추가
	@GetMapping("/list")
	public String list(Model model ,PagingVO pgvo) {
		log.info("list pgvo = {}" , pgvo);
		
		List<BoardVO> list = bsv.getList(pgvo);
		model.addAttribute("list", list);
		
		
		//페이징 처리
		//총 페이지 갯수 totalCount
		int totalCount = bsv.getTotalCount(pgvo);
		PagingHandler ph = new PagingHandler(pgvo, totalCount);
		log.info("pgvo = {}" , pgvo);
		log.info("ph = {}", ph);
		log.info("totalCount = {}", totalCount);
		model.addAttribute("ph",ph);
		
		
		return "/board/list";
	}
	
	@GetMapping("/cntdetail")
	public String nodetail(@RequestParam("bno")Long bno, Model model) {
		log.info("detail Modify bno = {}", bno);
		
		BoardVO bvo = bsv.cntdetail(bno);

		model.addAttribute("bvo",bvo);
		return "/board/detail";
	}
	
	@GetMapping({"/detail","modify"})
	public void detail(@RequestParam("bno")Long bno, Model model) {
		log.info("detail Modify bno = {}", bno);
		
		BoardVO bvo = bsv.detail(bno);
		model.addAttribute("bvo",bvo);
	}
	
	
	@PostMapping("/modify")
	public String modify(@ModelAttribute BoardVO bvo, Model model, RedirectAttributes rttr) {
		log.info("Modify bvo = {}", bvo);
		
		int isOk = bsv.modify(bvo);
		log.info("register = {} ", (isOk > 0 ? "Ok" : "Fail"));
		rttr.addAttribute("bno", bvo.getBno());
		rttr.addFlashAttribute("isOk", isOk);
		return "redirect:/board/detail";
	}
	
	
	@GetMapping("/remove")
	public String remove(@RequestParam("bno")Long bno, RedirectAttributes rttr) {
		
		int isOk = bsv.remove(bno);
		log.info("register = {} ", (isOk > 0 ? "Ok" : "Fail"));
		rttr.addFlashAttribute("isOk", isOk);
		return "redirect:/board/list";
	}
	
	
}
