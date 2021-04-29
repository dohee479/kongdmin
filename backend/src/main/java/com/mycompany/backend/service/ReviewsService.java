package com.mycompany.backend.service;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import com.mycompany.backend.dao.ReviewsDao;
import com.mycompany.backend.dto.Review;

@Service
public class ReviewsService {

	@Autowired
	private ReviewsDao reviewsDao;
	
	public int getCount(HashMap<String, Object> searchReview) {
		return reviewsDao.count(searchReview);
	}
	
	public List<Review> getReviewList(HashMap<String, Object> searchReview) {
		return reviewsDao.getReviewList(searchReview); 
	}
	
	public void getReviewImg(int review_id, HttpServletResponse response) {
		try {
			Review review = reviewsDao.getReview(review_id);
			String reviewAttachoname = review.getReview_attachoname();
			if (reviewAttachoname == null) {
				return;
			}
			reviewAttachoname = new String(reviewAttachoname.getBytes("UTF-8"),"ISO-8859-1");
			String reviewAttachsname = review.getReview_attachsname();
			String reviewAttachspath = "D:/kong/review/" + reviewAttachsname;
			String reviewAttachtype = review.getReview_attachtype();
			
			response.setHeader("Content-Disposition", "attachment; filename=\""+ reviewAttachoname +"\";");
			response.setContentType(reviewAttachtype);
			
            InputStream is = new FileInputStream(reviewAttachspath);
            OutputStream os = response.getOutputStream();
            FileCopyUtils.copy(is, os);
            is.close();
            os.flush();
            os.close();
		}  catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void delete(List<Integer> deleteId) {
		reviewsDao.delete(deleteId);
		
	}
}
