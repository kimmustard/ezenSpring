package com.ezen.myproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ezen.myproject.domain.CommentVO;
import com.ezen.myproject.service.CommentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/comment/*")
@Controller
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
//	@PostMapping("/post")
//	public CommentVO postV1(@RequestBody CommentVO cvo){
//		log.info("cvo = {}", cvo);
//		//DB연결
//		int isOk = csv.post(cvo);
//		
//		//리턴시 response의 통신상태를 같이 리턴
//		return cvo;
//	}
//	
	
	
	
	
}
