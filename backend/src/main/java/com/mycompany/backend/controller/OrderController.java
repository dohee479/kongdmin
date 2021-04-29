package com.mycompany.backend.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.backend.dao.OrderProductDao;
import com.mycompany.backend.dto.Order;
import com.mycompany.backend.dto.OrderProduct;
import com.mycompany.backend.service.OrderProductService;
import com.mycompany.backend.service.OrdersService;
import com.mycompany.backend.dto.Pager;

@RestController
@RequestMapping("/order")
public class OrderController {
	private final Logger logger = LoggerFactory.getLogger(OrderController.class);
	
	@Autowired
	private OrdersService ordersService;
	
	@Autowired
	private OrderProductService orderProductService;
	
	@GetMapping("")
	public Map<String, Object> list(int pageNo, 
									int state, 
									String search_user_id, 
									int fromPrice, 
									int toPrice,
									String fromDate,
									String toDate,
									String sortDsc) {

		int totalRows = ordersService.getCount(state, search_user_id, fromPrice, toPrice, fromDate, toDate);
		Pager pager =  new Pager(5, 5, totalRows, pageNo);
		List<Order> list = ordersService.getList(pager, state, search_user_id, fromPrice, toPrice, fromDate, toDate, sortDsc);
		Map<String, Object> map = new HashMap<>();
		
		for(Order item : list) {
			String productId = ordersService.getMainProduct(item.getOrder_id());
			int productCount = ordersService.getProductCount(item.getOrder_id());
			item.setOrder_product_count(productCount);
			item.setOrder_main_product(productId);
		}
		
		List<Integer> stateCountList = new ArrayList<>();
		int stateNumber[] = {1,2,3,5,9};
		
		for( int i : stateNumber ) 
			stateCountList.add(ordersService.getStateCount(i, search_user_id, fromPrice, toPrice, fromDate, toDate));
		
		map.put("pager", pager);
		map.put("order", list);
		map.put("state", stateCountList);
		return map;
	}
	
   @GetMapping("/{order_id}")
    public Map<String, Object> read(@PathVariable int order_id) {
    	Order order = ordersService.getOrder(order_id);
    	List<OrderProduct> list = orderProductService.getListByOrderId(order_id);
    	Map<String, Object> map = new HashMap<>();
    	map.put("order", order);
    	map.put("orderProducts", list);
    	return map;
    }
   
   @GetMapping("/mainProduct/{order_id}")
   public Map<String, Object> getMainProduct(@PathVariable int order_id) {
	   String mainProduct = ordersService.getMainProduct(order_id);
	   Map<String, Object> map = new HashMap<>();
	   map.put("mainProduct",mainProduct);
	   return map;
   }
   
   @PutMapping("/changeState")
   public Map<String, Object> changeState(int order_id, int changeState){
	   ordersService.changeState(order_id, changeState);
	   Order order = ordersService.getOrder(order_id);
   	   List<OrderProduct> list = orderProductService.getListByOrderId(order_id);
	   Map<String, Object> map = new HashMap<>();
	   map.put("order", order);
   	   map.put("orderProducts", list);
   	   return map;
   }
   
   @PutMapping("")
   public void updateOrder(@RequestBody Order order) {
	   if(order.getOrder_msg()==null) {
		   order.setOrder_msg("");
	   }
	   ordersService.updateOrder(order);
   }
}
