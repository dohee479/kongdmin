package com.mycompany.backend.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.backend.dto.MonthlyData;
import com.mycompany.backend.dto.Order;
import com.mycompany.backend.dto.OrderCount;
import com.mycompany.backend.dto.Product;
import com.mycompany.backend.service.OrdersService;
import com.mycompany.backend.service.ProductsService;

@RestController
@RequestMapping("/home")
public class HomeController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private OrdersService ordersService;
	@Autowired
	private ProductsService productsService;
	
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
	@GetMapping("/ordercount")
	public int orderCount() {
		return ordersService.getOrderCount();
	}
	
	@GetMapping("/productcountry")
	public List<Product> productByCountry(String product_country) {
		return productsService.getProductByCountry(product_country);
	}
	@GetMapping("/producttaste")
	public List<Product> productByTaste(String product_taste) {
		return productsService.getProductByTaste(product_taste);
	}
	@GetMapping("/productcount")
	public int productcount() {
		return productsService.getCount();
	}
	@GetMapping("/dateprice")
	public List<MonthlyData> datePrice(String order_month) {
		return ordersService.getDatePrice(order_month);
	}
	@GetMapping("/orderlist")
	public List<Order> orderByDateList(String order_date){
		return ordersService.getOrderByDate(order_date);
	}
}
