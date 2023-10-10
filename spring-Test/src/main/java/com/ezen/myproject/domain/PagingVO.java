package com.ezen.myproject.domain;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@ToString
@Setter
@Getter
public class PagingVO {

	private int pageNo;	//현재 화면에 표현되는 페이지번호
	private int qty;	//한 페이지당 보여지는 게시글 수 (10개) Quantity
	
	private String type;	//검색 타입
	private String keyword;	//검색어
	
	
	public PagingVO() {
		this(1,10);
	}
	
	public PagingVO(int pageNo, int qty) {
		this.pageNo = pageNo;
		this.qty = qty;
	}

	/**
	 * DB 상에서 limit의 시작값을 구하는 메서드
	 * limit 는 0부터 시작 10개씩  limit 0, 10 
	 */
	public int getPageStart() {
		return (this.pageNo - 1) * qty;
	}
	
	/**
	 * 여러가지 타입을 같이 검색하기 위해서 타입을 배열로 구분하는 메서드
	 */
	public String[] getTypeToArray() {
		return this.type == null ? new String[] {} : this.type.split("");
	}
	


}
