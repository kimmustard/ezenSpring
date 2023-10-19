package com.myweb.www.repository;

import java.util.List;

import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.PagingVO;

public interface BoardDAO {

	int insert(BoardVO bvo);

	List<BoardVO> getList(PagingVO pgvo);

	BoardVO detail(Long bno);
	BoardVO cntdetail(Long bno);
	void readdetail(Long bno);

	int modify(BoardVO bvo);

	int remove(Long bno);

	int getTotalCount(PagingVO pgvo);





}
