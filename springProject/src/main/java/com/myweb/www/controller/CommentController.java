package com.myweb.www.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myweb.www.domain.CommentVO;
import com.myweb.www.service.CommentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/comment/*")
@RequiredArgsConstructor
public class CommentController {
	
	private final CommentService csv;
	
	
	@PostMapping(value = "/post", consumes = "application/json", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> post(@RequestBody @Valid CommentVO cvo , BindingResult bindingResult){
		log.info("cvo = {}" , cvo);
		if (bindingResult.hasErrors()) {
			log.info("bindingResult = {}", bindingResult);
			return new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR) ;
		}
		
		log.info("bindingResult = {}", bindingResult);
		int isOk = csv.post(cvo);
		return isOk > 0 ? new ResponseEntity<String>("1", HttpStatus.OK) :
			 new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR) ;
		
	}
	
	@GetMapping(value = "/{bno}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CommentVO>> spread(@PathVariable("bno")long bno){
		log.info("List bno = {}", bno);
		
		List<CommentVO> list = csv.getList(bno);
		
		return new ResponseEntity<List<CommentVO>> (list, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{cno}")
	public ResponseEntity<String> remove(@PathVariable("cno") long cno){
		
		int isOk = csv.remove(cno);
		
		return isOk > 0 ? new ResponseEntity<String>("1", HttpStatus.OK) :
			 new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR) ;
	}
	
	
}
