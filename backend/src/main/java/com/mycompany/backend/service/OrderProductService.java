package com.mycompany.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.backend.dao.OrderProductDao;
import com.mycompany.backend.dto.OrderProduct;

@Service
public class OrderProductService {
	
	@Autowired
	private OrderProductDao orderProductDao;
	
	public List<OrderProduct> getListByOrderId(int orders_order_id){
		List<OrderProduct> list=orderProductDao.selectAll(orders_order_id);
		return list;
	}
}
