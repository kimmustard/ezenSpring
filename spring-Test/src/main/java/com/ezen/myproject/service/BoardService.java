package com.ezen.myproject.service;

import java.util.List;

import com.ezen.myproject.domain.BoardDTO;
import com.ezen.myproject.domain.BoardVO;
import com.ezen.myproject.domain.CommentVO;
import com.ezen.myproject.domain.PagingVO;

public interface BoardService {

//	int register(BoardVO bvo);
	
	int register(BoardDTO bdto);

	List<BoardVO> getList(PagingVO pgvo);

	BoardVO getDetail(int bno);
	
	int modify(BoardVO bvo);

	int remove(int bno);

	int getTotalCount(PagingVO pgvo);

	void cmtCount(CommentVO cvo);

	void cmtDeCount(int cno, int bno);



	
}
