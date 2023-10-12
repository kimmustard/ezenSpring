package com.ezen.myproject.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ezen.myproject.domain.CommentVO;
import com.ezen.myproject.service.CommentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/comment/*")
public class CommentController {

	private CommentService csv;

	@Autowired
	public CommentController(CommentService csv) {
		this.csv = csv;
	}
	
	
	//ResponseEntity 객체 사용
	//@RequestBody : body값 추출
	//제네릭에 리스트를 보내는 경우 -> <List<CommentVO>>
	
	@PostMapping(value = "/post", consumes = "application/json", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> post (@RequestBody CommentVO cvo){
		log.info("cvo = {}", cvo);
		
		//DB 연결
		int isOk = csv.post(cvo);
		
		return isOk > 0 ? new ResponseEntity<String>("1", HttpStatus.OK)
				: new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	

	@GetMapping(value = "/{bno}", consumes = "application/json" , produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<List<CommentVO>> spread(@PathVariable("bno")int bno){
		log.info("comment bno = {}", bno);
		List<CommentVO> list = csv.getList(bno);
		
		return new ResponseEntity<List<CommentVO>> (list, HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
