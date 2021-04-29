package com.mycompany.backend.dto;

import org.springframework.web.multipart.MultipartFile;

public class Review {

	private int review_id;
	private String review_title;
	private String review_content;
	private String review_grade;
	private MultipartFile review_attach;
	private String review_attachoname;
	private String review_attachsname;
	private String review_attachtype;
	private String review_date;
	private String users_user_id;
	private int order_product_id;
	private int products_product_id;
	private String product_title;
	public int getReview_id() {
		return review_id;
	}
	public void setReview_id(int review_id) {
		this.review_id = review_id;
	}
	public String getReview_title() {
		return review_title;
	}
	public void setReview_title(String review_title) {
		this.review_title = review_title;
	}
	public String getReview_content() {
		return review_content;
	}
	public void setReview_content(String review_content) {
		this.review_content = review_content;
	}
	public String getReview_grade() {
		return review_grade;
	}
	public void setReview_grade(String review_grade) {
		this.review_grade = review_grade;
	}
	public MultipartFile getReview_attach() {
		return review_attach;
	}
	public void setReview_attach(MultipartFile review_attach) {
		this.review_attach = review_attach;
	}
	public String getReview_attachoname() {
		return review_attachoname;
	}
	public void setReview_attachoname(String review_attachoname) {
		this.review_attachoname = review_attachoname;
	}
	public String getReview_attachsname() {
		return review_attachsname;
	}
	public void setReview_attachsname(String review_attachsname) {
		this.review_attachsname = review_attachsname;
	}
	public String getReview_attachtype() {
		return review_attachtype;
	}
	public void setReview_attachtype(String review_attachtype) {
		this.review_attachtype = review_attachtype;
	}
	public String getReview_date() {
		return review_date;
	}
	public void setReview_date(String review_date) {
		this.review_date = review_date;
	}
	public String getUsers_user_id() {
		return users_user_id;
	}
	public void setUsers_user_id(String users_user_id) {
		this.users_user_id = users_user_id;
	}
	public int getOrder_product_id() {
		return order_product_id;
	}
	public void setOrder_product_id(int order_product_id) {
		this.order_product_id = order_product_id;
	}
	public int getProducts_product_id() {
		return products_product_id;
	}
	public void setProducts_product_id(int products_product_id) {
		this.products_product_id = products_product_id;
	}
	public String getProduct_title() {
		return product_title;
	}
	public void setProduct_title(String product_title) {
		this.product_title = product_title;
	}
	
}
	