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

	@NotBlank(message = "이메일을 입력하세요.")
	private String email;
	
	@NotBlank(message = "비밀번호를 입력해주세요.")
	private String pwd;
	
	@NotBlank(message = "닉네임을 입력해주세요.")
	private String nickName;
	
	private String regAt;
	private String lastLogin;
	private List<AuthVO> authList;	//여러개의 권한을 리스트로 관리
	
}
