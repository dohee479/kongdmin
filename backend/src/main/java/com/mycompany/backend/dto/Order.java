package com.mycompany.backend.dto;

import java.util.Date;

public class Order {
	
	private int order_id;
	private String order_receiver;
	private String users_user_id;
	private String order_tel;
	private int order_zipcode;
	private String order_address;
	private String order_detail_address;
	private Date order_date;
	private String order_msg;
	private int order_state;
	private String order_account_name;
	private String order_account;
	private int order_total_price;
	
	/*주문시작 : 1 배송중 : 2 배송완료 : 3 구매확정 : 4 취소/환불 :5*/
	
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public String getOrder_receiver() {
		return order_receiver;
	}
	public void setOrder_receiver(String order_receiver) {
		this.order_receiver = order_receiver;
	}
	public String getUsers_user_id() {
		return users_user_id;
	}
	public void setUsers_user_id(String users_user_id) {
		this.users_user_id = users_user_id;
	}
	public String getOrder_tel() {
		return order_tel;
	}
	public void setOrder_tel(String order_tel) {
		this.order_tel = order_tel;
	}
	public String getOrder_address() {
		return order_address;
	}
	public void setOrder_address(String order_address) {
		this.order_address = order_address;
	}
	public String getOrder_detail_address() {
		return order_detail_address;
	}
	public void setOrder_detail_address(String order_detail_address) {
		this.order_detail_address = order_detail_address;
	}
	public Date getOrder_date() {
		return order_date;
	}
	public void setOrder_date(Date order_date) {
		this.order_date = order_date;
	}
	public String getOrder_msg() {
		return order_msg;
	}
	public void setOrder_msg(String order_msg) {
		this.order_msg = order_msg;
	}
	public int getOrder_state() {
		return order_state;
	}
	public void setOrder_state(int order_state) {
		this.order_state = order_state;
	}
	public String getOrder_account_name() {
		return order_account_name;
	}
	public void setOrder_account_name(String order_account_name) {
		this.order_account_name = order_account_name;
	}
	public String getOrder_account() {
		return order_account;
	}
	public void setOrder_account(String order_account) {
		this.order_account = order_account;
	}
	public int getOrder_total_price() {
		return order_total_price;
	}
	public void setOrder_total_price(int order_total_price) {
		this.order_total_price = order_total_price;
	}
	public int getOrder_zipcode() {
		return order_zipcode;
	}
	public void setOrder_zipcode(int order_zipcode) {
		this.order_zipcode = order_zipcode;
	}

	
	
	
}
