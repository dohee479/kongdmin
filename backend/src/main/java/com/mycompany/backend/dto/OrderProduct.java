package com.mycompany.backend.dto;

public class OrderProduct {
	
	private int order_product_id;
	private int order_product_volume;
	private int order_product_grind;
	private int order_product_count;
	private int orders_order_id;
	private int products_product_id;
	private int order_product_price;
	private String product_title;
	private int order_state;
	
	public int getOrder_product_id() {
		return order_product_id;
	}
	public void setOrder_product_id(int order_product_id) {
		this.order_product_id = order_product_id;
	}
	public int getOrder_product_volume() {
		return order_product_volume;
	}
	public void setOrder_product_volume(int order_product_volume) {
		this.order_product_volume = order_product_volume;
	}
	public int getOrder_product_grind() {
		return order_product_grind;
	}
	public void setOrder_product_grind(int order_product_grind) {
		this.order_product_grind = order_product_grind;
	}
	public int getOrder_product_count() {
		return order_product_count;
	}
	public void setOrder_product_count(int order_product_count) {
		this.order_product_count = order_product_count;
	}
	public int getOrders_order_id() {
		return orders_order_id;
	}
	public void setOrders_order_id(int orders_order_id) {
		this.orders_order_id = orders_order_id;
	}
	public int getProducts_product_id() {
		return products_product_id;
	}
	public void setProducts_product_id(int products_product_id) {
		this.products_product_id = products_product_id;
	}
	public int getOrder_product_price() {
		return order_product_price;
	}
	public void setOrder_product_price(int order_product_price) {
		this.order_product_price = order_product_price;
	}
	public String getProduct_title() {
		return product_title;
	}
	public void setProduct_title(String products_product_title) {
		this.product_title = products_product_title;
	}
	public int getOrder_state() {
		return order_state;
	}
	public void setOrder_state(int order_state) {
		this.order_state = order_state;
	}

}
