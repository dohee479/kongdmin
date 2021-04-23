package com.mycompany.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.backend.dao.OrderDao;
import com.mycompany.backend.dto.Order;
import com.mycompany.backend.dto.Pager;

@Service
public class OrderService {

	@Autowired
	private OrderDao orderDao;
	
	public int getCount(int state, String search_user_id) {
		return orderDao.count(state, search_user_id);
	}
	
	public List<Order> getList(Pager pager, int state, String search_user_id) {
	    return orderDao.selectByPage(pager, state, search_user_id);
	}
	
	public int getStateCount(int state) {
		return orderDao.selectStateCountByState(state);
	}
	
	public Order getOrder(int order_id) {
		return orderDao.selectByOrderId(order_id);
	}
	
	public String getMainProduct(int order_id) {
		return orderDao.selectMainProductByOrderId(order_id);
	}
	
}
