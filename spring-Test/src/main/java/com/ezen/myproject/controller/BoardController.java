package com.ezen.myproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ezen.myproject.domain.BoardDTO;
import com.ezen.myproject.domain.BoardVO;
import com.ezen.myproject.domain.FileVO;
import com.ezen.myproject.domain.PagingVO;
import com.ezen.myproject.handler.FileHandler;
import com.ezen.myproject.handler.PagingHandler;
import com.ezen.myproject.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/board/*")
@Controller
public class BoardController{

	
	private BoardService bsv;
	
	private FileHandler fhd;
	

	@Autowired
	public BoardController(BoardService bsv, FileHandler fhd) {
		this.bsv = bsv;
		this.fhd = fhd;
	}


	@GetMapping("/register")
	public String boardRegisterGet() {
		return "/board/register";
	}
	
	
//	@PostMapping("/register")
//	public String register(BoardVO bvo) {
//		log.info("bvo = {}" , bvo);
//		int isOk = bsv.register(bvo);
//		log.info("register = {} ", (isOk > 0 ? "Ok" : "Fail"));
//		return "redirect:/board/list";
//	}
	
	//required (필수여부) = false : 해당 파라미터가 없어도 예외가 발생하지 않음.
	@PostMapping("/register")
	public String register(BoardVO bvo,
			@RequestParam(name = "files", required = false )MultipartFile[] files) {
		log.info("bvo = {}" , bvo.toString());
		log.info("files = {}" , files.toString());
		
		
		
		List<FileVO> flist = null;
		
		//files가 null 일 수 있음. (첨부파일이 있을 경우에만 fhd를 호출)
		if(files[0].getSize() > 0) {
			//첫번째 파일의 size가 0보다 크다면..?
			//flist에 파일 객체 담기
			flist = fhd.uploadFiles(files);
		}else {
			log.info("file null");
		}
		
		BoardDTO bdto = new BoardDTO(bvo, flist);

		int isOk = bsv.register(bdto);
		log.info("register = {} ", (isOk > 0 ? "Ok" : "Fail"));
		
		
		
		return "redirect:/board/list";
	}
	
	
	@GetMapping("/list")
	public String list(Model model, PagingVO pgvo) {
		log.info("PagingVO = {} ", pgvo);
		
		// 카운터를 넣어주는 조회
		// update board set commentCount = ( select count(cno) from comment where bno = board.bno);
		
		// DB로 getList(pgvo);
		List<BoardVO> list = bsv.getList(pgvo);
		
		model.addAttribute("list",list);
		
		// cnt 
		int totalCount = bsv.getTotalCount(pgvo);
		
		PagingHandler ph = new PagingHandler(pgvo, totalCount);
		
		model.addAttribute("ph", ph);
		log.info("list check end");
		
		return "/board/list";
	}
	
	
	@GetMapping({"/detail","/modify"})
	public void detail(
			Model model, 
			@RequestParam("bno")int bno
			) {
		log.info("detail bno = {}", bno);
		// 파일내용도 포함해서 같이 가져가야 함.
		
//		BoardVO bvo = bsv.getDetail(bno);
		BoardDTO bdto = bsv.getDetailFile(bno);
		model.addAttribute("boardDTO", bdto);
	}

	
	//수정할 때 들어가는 부당 readCount 2개
	@PostMapping("/modify")
	public String modify(BoardVO bvo, RedirectAttributes rttr,
			@RequestParam(name = "files", required = false) MultipartFile[] files) {
		log.info("modify bvo = {}" , bvo);
		log.info("files = {}", files);
		
		
		List<FileVO> flist = null;
		if(files[0].getSize() > 0) {
			//기존 file은 이미 DB에 존재함. 삭제할 파일은 비동기로 이미 삭제 완료
			//새로 추가할 파일만 추가 하면된다.
			flist = fhd.uploadFiles(files);	// fvo 구성 후 List로 리턴
		}
		BoardDTO bdto = new BoardDTO(bvo, flist);
		int isOk = bsv.modifyFile(bdto);
		
		log.info("modify = {} ", (isOk > 0 ? "Ok" : "Fail"));
		return "redirect:/board/detail?bno=" + bvo.getBno();
	}
	
	
	@GetMapping("/remove")	
	public String remove(@RequestParam("bno")int bno, RedirectAttributes rttr) {
		log.info("remove bno = {}", bno);
		int isOk = bsv.remove(bno);
		log.info("remove = {} ", (isOk > 0 ? "Ok" : "Fail"));
		rttr.addFlashAttribute("isOk", isOk);
		return "redirect:/board/list";
	}
	

	@DeleteMapping(value = "/file/{uuid}", produces = MediaType.TEXT_PLAIN_VALUE )	
	public ResponseEntity<String> removeFile(@PathVariable("uuid") String uuid){
		log.info("uuid = {} " ,uuid);
		
		int isOk = bsv.removeFile(uuid);
		
		return isOk > 0 ? new ResponseEntity <String> ("1", HttpStatus.OK)
				: new ResponseEntity<String> ("0", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	

	
}
