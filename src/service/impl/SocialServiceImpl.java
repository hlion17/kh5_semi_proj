package service.impl;

import javax.servlet.http.HttpServletRequest;

import dto.Member;
import dto.SocialMember;
import service.face.SocialService;

public class SocialServiceImpl implements SocialService {

	@Override
	public SocialMember getMemberno(HttpServletRequest req) {
		System.out.println("[TEST] SocialServiceImpl - getMemberno(HttpServletRequest req) 호출");
		//전달파라미터 boardno를 저장할 DTO객체 생성
		SocialMember memberno = new SocialMember();
		
		String param = req.getParameter("memberno");
		if( param != null && !"".equals( param ) ) {
			memberno.setMemberno( Integer.parseInt(param) );
		} else {
			System.out.println("[WARN] MemberService getMemberno() - memberno값이 null이거나 비어있음");
		}
		
		System.out.println("[TEST] SocialServiceImpl - getMemberno(HttpServletRequest req) 리턴 memberno : " + memberno);
		return memberno;
	}

}
