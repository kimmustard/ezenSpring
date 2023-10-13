package com.ezen.myproject.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ezen.myproject.domain.BoardVO;
import com.ezen.myproject.domain.CommentVO;
import com.ezen.myproject.domain.PagingVO;


public interface BoardDAO {

	
	int insert(BoardVO bvo);

	List<BoardVO> getList(PagingVO pgvo);

	BoardVO getDetail(int bno);

	void readCount(@Param("bno")int bno, @Param("cnt")int cnt);

	int modify(BoardVO bvo);

	int remove(int bno);

	int getTotalCount(PagingVO pgvo);

	void cmtCount(CommentVO cvo);

	void cmtDeCount(@Param("cno")int cno, @Param("bno")int bno);
	
	void fileCount(@Param("bno")int bno, @Param("cntFile") int cntFile);
	
	int selectBno();

	void updateCommentCount();



}
