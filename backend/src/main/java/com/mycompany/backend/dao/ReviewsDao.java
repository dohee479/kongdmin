package com.mycompany.backend.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycompany.backend.dto.Review;

@Mapper
public interface ReviewsDao {
	public int count(HashMap<String, Object> searchReview);
	public List<Review> getReviewList(HashMap<String, Object> searchReview);
	public Review getReview(int review_id);
	public void delete(List<Integer> deleteId);
}
