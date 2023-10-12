package com.ezen.myproject.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class PagingVO {

	private int pageNo;	// 현재 화면에 표현되는 페이지 번호
	private int qty; // 한 페이지당 보여지는 게시글 수 (10개) 
	
	private String type; //검색타입
	private String keyword; //검색어
	
	
	
	// 초기값 pageNO = 1 , qty = 10
	public PagingVO() {
		this(1,10);
	}
	
	
	public PagingVO(int pageNo, int qty) {
		this.pageNo = pageNo;
		this.qty = qty;
	}


	/*
	 * DB 상에서 limit의 시작 값을 구하는 메서드
	 * limit은 0부터 시작 10개씩 limit 0, 10
	 * 1 = 0부터 10개 , 2 = 10부터 10개 , 3 = 20부터 10개 . . . .
	 * 
	 */
	public int getPageStart() {
		return (this.pageNo -1) * qty;
	}
	
	
	
	/*
	 * 여러가지 타입을 같이 검색하기 위해서 타입을 뱅려로 구분하는 메서드
	 */
	public String[] getTypeToArray() {
		return this.type == null ? new String[] {} : this.type.split("");
	}
	
	
}
