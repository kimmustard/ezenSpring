package com.ezen.myproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.myproject.domain.CommentVO;
import com.ezen.myproject.repository.CommentDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CommentServiceImpl implements CommentService{

	private CommentDAO cdao;

	
	@Autowired
	public CommentServiceImpl(CommentDAO cdao) {
		this.cdao = cdao;
	}


	@Override
	public int post(CommentVO cvo) {
		log.info("comment post check1");
		return cdao.insert(cvo);
	}
	
	
	
	
}
