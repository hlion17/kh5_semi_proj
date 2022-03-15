package service.impl;

import java.sql.Connection;
import java.util.List;

import common.JDBCTemplate;
import dao.face.IngrDao;
import dao.impl.IngrDaoImpl;
import dto.Ingredient;
import service.face.IngrService;

public class IngrServiceImpl implements IngrService {

    private IngrDao ingrDao = new IngrDaoImpl();

    @Override
    public List<Ingredient> getAllIngrs() {

        Connection conn = JDBCTemplate.getConnection();

        List<Ingredient> ingrs = ingrDao.findAll(conn);

        return ingrs;
    }
}
