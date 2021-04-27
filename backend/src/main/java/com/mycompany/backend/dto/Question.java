package com.mycompany.backend.dto;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Question {
	
	private int question_id;
	private String question_title;
	private String question_content;
	private Date question_date;
	private String users_user_id;
	private int products_product_id;
	private String str_date;
	//product테이블 join
	private String product_title;
	private String question_answer;
	
	public int getQuestion_id() {
		return question_id;
	}
	public void setQuestion_id(int question_id) {
		this.question_id = question_id;
	}
	public String getQuestion_title() {
		return question_title;
	}
	public void setQuestion_title(String question_title) {
		this.question_title = question_title;
	}
	public String getQuestion_content() {
		return question_content;
	}
	public void setQuestion_content(String question_content) {
		this.question_content = question_content;
	}
	public Date getQuestion_date() {
		return question_date;
	}
	public void setQuestion_date(Date question_date) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		setStr_date(dateFormat.format(question_date));
		this.question_date = question_date;
	}
	public String getUsers_user_id() {
		return users_user_id;
	}
	public void setUsers_user_id(String users_user_id) {
		this.users_user_id = users_user_id;
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
	public String getStr_date() {
		return str_date;
	}
	public void setStr_date(String str_date) {
		this.str_date = str_date;
	}
	public String getQuestion_answer() {
		return question_answer;
	}
	public void setQuestion_answer(String question_answer) {
		this.question_answer = question_answer;
	}
	
}
