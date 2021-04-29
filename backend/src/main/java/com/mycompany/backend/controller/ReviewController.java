package com.mycompany.backend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.backend.dto.Pager;
import com.mycompany.backend.dto.Review;
import com.mycompany.backend.service.ReviewsService;

@RestController
@RequestMapping("/review")
public class ReviewController {
	
	@Autowired
	private ReviewsService reviewsService;

	@GetMapping("")
	public Map<String, Object> List(String keyword, String kcontent, @RequestParam(defaultValue="1") int pageNo, String sort) {
		System.out.println(keyword);
		System.out.println(kcontent);
		System.out.println(pageNo);
		HashMap<String, Object> searchReview = new HashMap<>();
		searchReview.put("keyword", keyword);
		searchReview.put("kcontent", kcontent);
		searchReview.put("sort", sort);
		int totalRows = reviewsService.getCount(searchReview);
		Pager pager = new Pager(10, 5, totalRows, pageNo);
		searchReview.put("startRowNo", pager.getStartRowNo());
		searchReview.put("endRowNo", pager.getEndRowNo());
		System.out.println(searchReview);
		List<Review> reviewList = reviewsService.getReviewList(searchReview);
		Map<String, Object> reviewMap = new HashMap<>();
		reviewMap.put("pager", pager);
		reviewMap.put("reviewList", reviewList);
		return reviewMap;
	}
	
	@GetMapping("/review-attach/{review_id}")
	public void reviewImg(@PathVariable int review_id, HttpServletResponse response) {
		reviewsService.getReviewImg(review_id, response);
	}
	
	@DeleteMapping("/delete")
	public void delete(@RequestParam List<Integer> deleteId) {
		System.out.println(deleteId);
		reviewsService.delete(deleteId);
	}
}
