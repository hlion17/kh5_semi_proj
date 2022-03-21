package service.face;

import javax.servlet.http.HttpServletRequest;

import dto.Member;
import dto.SocialMember;

public interface SocialService {

	/**
	 * 요청 파라미터 얻어오기
	 * 
	 * @param req - 요청 정보 객체
	 * @return Member - 전달파라미터 memberno값을 포함한 DTO객체
	 */
	public SocialMember getMemberno(HttpServletRequest req);
}
