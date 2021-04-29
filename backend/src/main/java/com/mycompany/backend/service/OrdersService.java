package com.mycompany.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.backend.dao.OrdersDao;
import com.mycompany.backend.dto.MonthlyData;
import com.mycompany.backend.dto.Order;
import com.mycompany.backend.dto.OrderCount;
import com.mycompany.backend.dto.Pager;


@Service
public class OrdersService {
	
	@Autowired
	private OrdersDao ordersDao;
	
	public List<MonthlyData> getMonthPriceList() {
		return ordersDao.selectMonthPrice();
	}
	public List<MonthlyData> getMonthCountList(){
		return ordersDao.selectMonthCount();
	}
	public List<OrderCount> getCountryCountList(){
		return ordersDao.selectCountryCount();
	}
	public List<OrderCount> getTasteCountList(){
		return ordersDao.selectTasteCount();
	}
	public int getCount(int state, String search_user_id) {
		return ordersDao.count(state, search_user_id);
	}
	
	public List<Order> getList(Pager pager, int state, String search_user_id) {
	    return ordersDao.selectByPage(pager, state, search_user_id);
	}
	
	public int getStateCount(int state) {
		return ordersDao.selectStateCountByState(state);
	}
	
	public Order getOrder(int order_id) {
		return ordersDao.selectByOrderId(order_id);
	}
	
	public String getMainProduct(int order_id) {
		return ordersDao.selectMainProductByOrderId(order_id);
	}
	public int getOrderCount() {
		return ordersDao.selectOrderCount();
	}
	public List<MonthlyData> getDatePrice(String order_month){
		return ordersDao.selectDatePrice(order_month);
	}
	public List<Order> getOrderByDate(String order_date){
		return ordersDao.selectByDate(order_date);
	}
}
