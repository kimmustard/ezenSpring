package com.ezen.myproject.handler;

import com.ezen.myproject.domain.PagingVO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@ToString
public class PagingHandler {

	private int startPage;
	private int endPage;
	private boolean prev;
	private boolean next;
	private int totalCount;
	private int realEndPage;
	private PagingVO pgvo;
	
	
	public PagingHandler(PagingVO pgvo, int totalCount) {
		this.pgvo = pgvo;
		this.totalCount = totalCount;
		
		// 1 2 3 4 5  . . . . 10 => pageNo 1~10 까지는 endPage 가 10
		// 11 12 13 14 15 . . . . 20 => 20 / pageNo 11~20까지는 endPage가 20
		
		this.endPage = 
				(int) Math.ceil(pgvo.getPageNo() /(double) pgvo.getQty()) * pgvo.getQty();
		
		this.startPage = endPage - 9;
	
	
		realEndPage = (int) Math.ceil(totalCount /(double) pgvo.getQty());
		
		//endPage는 10, 20 , 30으로만 구성
		//realEndPage는 실제 마지막 페이지
		
		if(realEndPage < this.endPage) {
			this.endPage = realEndPage;
		}
		
		// startPage : 1, 11 ,21 . . . .
		this.prev = this.startPage > 1; 	//1보다 크면 true
		this.next = this.endPage < realEndPage;
		
	}
	
	
	
		

	
}
