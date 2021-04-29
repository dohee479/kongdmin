package com.mycompany.backend.dto;

public class User {

	private String user_id;
	private String user_name;
	private String user_password;
	private String user_tel;
	private String user_email;
	private String user_address;
	private String user_detail_address;
	private String user_zipcode;
	private String user_authority;
	private String user_enabled;
	private int total_pay;
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_password() {
		return user_password;
	}
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
	public String getUser_tel() {
		return user_tel;
	}
	public void setUser_tel(String user_tel) {
		this.user_tel = user_tel;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public String getUser_address() {
		return user_address;
	}
	public void setUser_address(String user_address) {
		this.user_address = user_address;
	}
	public String getUser_detail_address() {
		return user_detail_address;
	}
	public void setUser_detail_address(String user_detail_address) {
		this.user_detail_address = user_detail_address;
	}
	public String getUser_enabled() {
		return user_enabled;
	}
	public void setUser_enabled(String user_enabled) {
		this.user_enabled = user_enabled;
	}
	public String getUser_authority() {
		return user_authority;
	}
	public void setUser_authority(String user_authority) {
		this.user_authority = user_authority;
	}
	public String getUser_zipcode() {
		return user_zipcode;
	}
	public void setUser_zipcode(String user_zipcode) {
		this.user_zipcode = user_zipcode;
	}
	public int getTotal_pay() {
		return total_pay;
	}
	public void setTotal_pay(int total_pay) {
		this.total_pay = total_pay;
	}
	
	
}
