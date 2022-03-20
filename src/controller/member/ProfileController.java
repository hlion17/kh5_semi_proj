package controller.member;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.face.MemberDao;
import dao.impl.MemberDaoImpl;
import service.face.MemberService;
import service.impl.MemberServiceImpl;

/**
 * Servlet implementation class ProfilController
 */

@WebServlet("/member/profile")
public class ProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private MemberService memberService = new MemberServiceImpl();
//	private MemberDao memeberDao = new MemberDaoImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		// 작성글 삽입
		memberService.uploadProfil(req);
		
		//VIEW 지정
		req.getRequestDispatcher("/WEB-INF/views/member/profile.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
//		req.setCharacterEncoding("UTF-8");
//		resp.setContentType("text/html;charset=UTF-8");
//		MultipartRequest multi = null;
//		int fileMaxSize = 10 * 1024 * 1024;
//		String savePath = req.getRealPath("/upload").replaceAll("\\\\","/");
//		try {
//			multi = new MultipartRequest(req, savePath, fileMaxSize, "UTF-8", new DefaultFileRenamePolicy());
//		} catch(Exception e) {
//			req.getSession().setAttribute("messageType", "오류 메시지");
//			req.getSession().setAttribute("messageContent", "파일 크기는 10MB를 넘을 수 없습니다.");
//			resp.sendRedirect("/member/profile");
//			return;
//			
//		}
//		String memberid = multi.getParameter("memberid");
//		HttpSession session = req.getSession();
//		if(!memberid.equals((String)session.getAttribute("memberid"))) {
//			req.getSession().setAttribute("messageType", "오류 메시지");
//			req.getSession().setAttribute("messageContent", "파일 크기는 10MB를 넘을 수 없습니다.");
//			resp.sendRedirect("/main.jsp");
//			return;
//		} 
//		String fileName ="";
//		File file = multi.getFile("userProfile");
//		if(file != null) {
//			String ext = file.getName().substring(file.getName().lastIndexOf(".") + 1);
//			if(ext.equals("jpg") || ext.equals("png") || ext.equals("gif")) {
//				String prev = new MemberDao().getMemberid(memberid).getFileno();
//			} else {
//				
//			}
//		}
		
		
		
	}
}
