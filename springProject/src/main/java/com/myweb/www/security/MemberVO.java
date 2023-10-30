package com.myweb.www.security;

import java.util.List;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MemberVO {

	private String email;
	
	private String pwd;
	
	private String nickName;
	
	private String regAt;
	private String lastLogin;
	private List<AuthVO> authList;	//여러개의 권한을 리스트로 관리
	
}