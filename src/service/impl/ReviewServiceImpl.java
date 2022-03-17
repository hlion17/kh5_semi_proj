package service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.Review;
import dto.ReviewFile;
import service.face.ReviewService;
import util.Paging;

public class ReviewServiceImpl implements ReviewService {

	@Override
	public List<Review> getList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Review getreview_no(HttpServletRequest req) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Review review) {
		// TODO Auto-generated method stub

	}

	@Override
	public Review view(Review pro_no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getNick(Review viewReview) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReviewFile viewFile(Review viewReview) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void write(HttpServletRequest req) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(HttpServletRequest req) {
		// TODO Auto-generated method stub

	}

	@Override
	public Paging getPaging(HttpServletRequest req) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Review> getList(Paging paging) {
		// TODO Auto-generated method stub
		return null;
	}

}
