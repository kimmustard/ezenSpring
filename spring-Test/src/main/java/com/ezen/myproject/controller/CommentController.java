package com.ezen.myproject.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ezen.myproject.domain.CommentVO;
import com.ezen.myproject.domain.MemberVO;
import com.ezen.myproject.service.CommentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/comment/*")
@RestController
public class CommentController {

	private CommentService csv;

	@Autowired
	public CommentController(CommentService csv) {
		this.csv = csv;
	}

	//ResponseEntity 객체 사용
	//@RequestBody : body값을 추출 
	//제네릭에 리스트를 보낼때 -> <List<CommentVO>>
	/*
	 * value = "mapping name"
	 * consumes = " 가져오는 데이터 형태 "
	 * produces = " 보내는 데이터의 형식 " 나가는 데이터 타입은 반드시 -> mediaType으로 내보내야한다.
	 * json : application/json , text : text/plain
	 * 
	 * Accept
	 */
	
	@PostMapping(value="/post", consumes = "application/json" , produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> post (@RequestBody CommentVO cvo){
		log.info("cvo = {}", cvo);
		//DB연결
		int isOk = csv.post(cvo);
		
		//리턴시 response의 통신상태를 같이 리턴
		return isOk>0 ? new ResponseEntity<String>("1", HttpStatus.OK) 
				: new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
//	@ResponseBody
//	@ResponseStatus(HttpStatus.OK)
//	@PostMapping("/post")
//	public int postV1(@RequestBody CommentVO cvo){
//		log.info("cvo = {}", cvo);
//		//DB연결
//		int isOk = csv.post(cvo);
//		
//		//리턴시 response의 통신상태를 같이 리턴
//		return isOk;
//	}
	
	@GetMapping(value = "/{bno}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CommentVO>> spread(@PathVariable("bno") int bno){
		log.info("comment bno = {}", bno);
		List<CommentVO> list = csv.getList(bno);
		//DB 요청
		
		return new ResponseEntity <List<CommentVO>> (list, HttpStatus.OK);
	}
	
	
	
	@PutMapping(value = "/{cno}", consumes = "application/json", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> modify(@PathVariable("cno")int cno, @RequestBody CommentVO cvo,
			HttpServletRequest request) {
		
		//로그인 확인
		if(request.getSession().getAttribute("ses") == null) {
			log.info("modify if check");
			return new ResponseEntity<String> ("0", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		MemberVO mvo = (MemberVO) request.getSession().getAttribute("ses");
		String writer = mvo.getId(); 
		cvo.setCno(cno);
		cvo.setWriter(writer);
		log.info("test cvo = {}" , cvo);
		
	
		int isOk = csv.edit(cvo);
	
		
		return isOk > 0 ? new ResponseEntity<String> ("1", HttpStatus.OK)
				: new ResponseEntity<String> ("0", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	
	@DeleteMapping("/{cno}")
	public ResponseEntity<String> remove(@PathVariable("cno")int cno, HttpServletRequest request) {
		log.info("delete commnet cno = {}", cno);
		
		//로그인 확인
		if(request.getSession().getAttribute("ses") == null) {
			log.info("remove if check");
			return new ResponseEntity<String> ("0", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		MemberVO mvo = (MemberVO) request.getSession().getAttribute("ses");
		String writer = mvo.getId(); 
		
		int isOk = csv.remove(cno);
		

		return isOk > 0 ? new ResponseEntity <String> ("1", HttpStatus.OK)
				: new ResponseEntity<String> ("0", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
}
