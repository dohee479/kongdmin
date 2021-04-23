package com.mycompany.backend.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.backend.dto.MonthlyData;
import com.mycompany.backend.dto.OrderCount;
import com.mycompany.backend.service.OrdersService;

@RestController
@RequestMapping("/home")
public class homeController {
	private static final Logger logger = LoggerFactory.getLogger(homeController.class);
	
	@Autowired
	private OrdersService ordersService;
	
	
	@GetMapping("/monthprice")
	public List<MonthlyData> monthList() {
		return ordersService.getMonthPriceList();
	}
	
	@GetMapping("/monthcount")
	public List<MonthlyData> monthCount(){
		return ordersService.getMonthCountList();
	}
	@GetMapping("/countrycount")
	public List<OrderCount> countryCount(){
		return ordersService.getCountryCountList();
	}
	@GetMapping("/tastecount")
	public List<OrderCount> tasteCount(){
		return ordersService.getTasteCountList();
	}
}
