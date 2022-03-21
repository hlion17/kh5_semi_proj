package service.impl;


import java.util.List;


import common.JDBCTemplate;
import dao.face.PaymentDao;
import dao.impl.PaymentDaoImpl;
import dto.Cart;
import dto.Payment;
import service.face.PaymentService;

public class PaymentServiceImpl implements PaymentService {

	private PaymentDao paymentDao = new PaymentDaoImpl();
	
}
