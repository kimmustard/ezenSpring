package com.ezen.myproject.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ezen.myproject.domain.MemberVO;
import com.ezen.myproject.repository.MemberDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MemberServiceImpl implements MemberService{

	private MemberDAO mdao;
	
	//password Encode를 하기위한 security 디펜던시 추가
	BCryptPasswordEncoder passwordEncoder;
	HttpServletRequest request;

	@Autowired
	public MemberServiceImpl(MemberDAO mdao, BCryptPasswordEncoder passwordEncoder, HttpServletRequest request) {
		this.mdao = mdao;
		this.passwordEncoder = passwordEncoder;
		this.request = request;
	}
	
	@Override
	public int signup(MemberVO mvo) {
		log.info("signup check 2");
		
		// 아이디가 중복되면 회원가입 실패
		// 중복이 아니라면 아이디를 주고, DB에서 일치하는 유저를 달라고 요청
		MemberVO temp = mdao.getUser(mvo.getId());
		if(temp != null) {
			log.info("회원 아이디가 이미 존재합니다.");
			return 0;
		}
		
		//아이디가 중복되지 않는다면 회원가입 진행
		//password가 null이면, 혹은 값이 ""면 가입 불가
		if(mvo.getId() == null || mvo.getId().length() == 0) {
			log.info("아이디 값이 없습니다.");
			return 0;
		}
		if(mvo.getPw() == null || mvo.getPw().length() == 0) {
			log.info("비밀번호 값이 없습니다.");
			return 0;
		}
		
		//회원가입을 진행하는데 암호화를 먼저 해야하한다.
		//암호화 (encode / matches 원래 비밀번호 , 암호화된 비밀번호 ) => true / false
		String pw = mvo.getPw();
		
		String encodepw = passwordEncoder.encode(pw); // 패스워드 암호화
		
		mvo.setPw(encodepw); // 멤버객체에 암호화된 패스워드로 변경
		
		return mdao.signup(mvo);
	}

	@Override
	public MemberVO isUser(MemberVO mvo) {
		//로그인 유저 확인 메서드
		// 아이디를 주고, 해당 아이디의 객체 가져오기
		MemberVO temp = mdao.getUser(mvo.getId());
		
		// 해당 아이디의 객체가 없는 경우
		if(temp == null) {
			return null;
		}
		
		//passwordencoder.matches (원래비밀번호, 암호화된 비밀번호) : 매치가 되는지 체크
		//DB에서 꺼내온 temp.getPW() 와 방금 로그인한 mvo.getPw()를 비교
		// 맞으면 true / 틀리면 false
		if(passwordEncoder.matches(mvo.getPw(), temp.getPw())) {	
			return temp;
		}else {
			return null;
		}
		
	}

	@Override
	public int modify(MemberVO mvo) {
		// mvo 객체에서 pw의 값이 있는지 체크
		// mvo의 pw객체가 없다면 기존 값으로세팅 , 있으면 변경
		if(mvo.getPw() == null || mvo.getPw().length() == 0) {
			MemberVO sesMVO = (MemberVO) request.getSession().getAttribute("ses");
			mvo.setPw(sesMVO.getPw());
		}else {
			String setpw = passwordEncoder.encode(mvo.getPw());
			mvo.setPw(setpw);
		}
		return mdao.update(mvo);
	}
	
	
	
}
