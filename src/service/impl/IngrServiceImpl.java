package service.impl;

import java.sql.Connection;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import common.JDBCTemplate;
import dao.face.IngrDao;
import dao.impl.IngrDaoImpl;
import dto.Ingredient;
import service.face.IngrService;

public class IngrServiceImpl implements IngrService {

    private IngrDao ingrDao = new IngrDaoImpl();
    private static Logger logger = Logger.getLogger(IngrServiceImpl.class.getName());

    @Override
    public List<Ingredient> getAllIngrs() {

        Connection conn = JDBCTemplate.getConnection();

        List<Ingredient> ingrs = ingrDao.findAll(conn);

        return ingrs;
    }

	@Override
	public void getIngrDetail(HttpServletRequest req) {
		// 요청파라미터 분석
		int ingrCode = Integer.parseInt(req.getParameter("ingrCode"));
		logger.info("재료상세 - 요청파라미터:" + ingrCode);
		
		// DB Connection 생성
		Connection conn = JDBCTemplate.getConnection();
		
		// DB에 재료번호로 상세내용 조회
		Ingredient ingr = ingrDao.findById(conn, ingrCode);
		logger.info("재료상세 - DB조회 결과: " + ingr);
		
		// View 에서 보여질 DB 조회결과 요청객체에 저장
		req.setAttribute("ingr", ingr);
	}

	@Override
	public void findByIngrName(HttpServletRequest req) {
		// 요청파라미터 분석
		String ingrName = req.getParameter("ingrName");
    	logger.info("[POST]/ingr/list - 요청 파라미터 ingrName: " + ingrName);
		
		// DB Connection 생성
		Connection conn = JDBCTemplate.getConnection();
		
		// DB에 재료명으로 조회
		List<Ingredient> ingrs = ingrDao.findByName(conn, ingrName);
		
		// View에서 보여질 DB 조회결과(List) 저장
		req.setAttribute("ingrs", ingrs);
		
	}
}
