package com.myweb.www.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.myweb.www.domain.BoardVO;
import com.myweb.www.repository.BoardDAO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class BaordServiceImpl implements BoardService{

	private final BoardDAO bdao;

	@Override
	public int insert(BoardVO bvo) {
		
		return bdao.insert(bvo);
	}

	@Override
	public List<BoardVO> getList() {
		
		return bdao.getList();
	}

	@Override
	public BoardVO detail(Long bno) {
		
		return bdao.detail(bno);
	}

	@Override
	public int modify(BoardVO bvo) {
		
		return bdao.modify(bvo);
	}

	@Override
	public int remove(Long bno) {
		return bdao.remove(bno);
	}

	@Override
	public BoardVO cntdetail(Long bno) {
		bdao.readdetail(bno);
		return bdao.cntdetail(bno);
	}

}
