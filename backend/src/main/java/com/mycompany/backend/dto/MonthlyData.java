package com.mycompany.backend.dto;

import java.util.Date;

public class MonthlyData {
	private String month;
	private int month_total_price;
	private int month_count;

	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public int getMonth_total_price() {
		return month_total_price;
	}
	public void setMonth_total_price(int month_total_price) {
		this.month_total_price = month_total_price;
	}
	public int getMonth_count() {
		return month_count;
	}
	public void setMonth_count(int month_count) {
		this.month_count = month_count;
	}

}
