package com.mycompany.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.backend.dao.OrdersDao;
import com.mycompany.backend.dto.MonthlyData;
import com.mycompany.backend.dto.OrderCount;


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

}
