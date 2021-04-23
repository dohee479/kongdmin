package com.mycompany.backend.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.backend.dao.OrderProductDao;
import com.mycompany.backend.dto.Order;
import com.mycompany.backend.dto.OrderProduct;
import com.mycompany.backend.service.OrderProductService;
import com.mycompany.backend.service.OrderService;
import com.mycompany.backend.dto.Pager;

@RestController
@RequestMapping("/order")
public class OrderController {
	private final Logger logger = LoggerFactory.getLogger(OrderController.class);
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderProductService orderProductService;
	
	@GetMapping("")
	public Map<String, Object> list(int pageNo, int state, String search_user_id) {
		logger.info(state+"");
		logger.info(search_user_id+"");
		int totalRows = orderService.getCount(state, search_user_id);
		Pager pager =  new Pager(5, 5, totalRows, pageNo);
		List<Order> list = orderService.getList(pager, state, search_user_id);
		Map<String, Object> map = new HashMap<>();
		
		List<Integer> stateCountList = new ArrayList<>();
		int stateNumber[] = {1,5,9};
		
		for( int i : stateNumber ) 
			stateCountList.add(orderService.getStateCount(i));
		
		map.put("pager", pager);
		map.put("order", list);
		map.put("state", stateCountList);
		return map;
	}
	
//	@GetMapping("")
//	public Map<String, Object> list(int pageNo, int state, String search_user_id){
//		
//	}
	
   @GetMapping("/{order_id}")
    public Map<String, Object> read(@PathVariable int order_id) {
    	Order order = orderService.getOrder(order_id);
    	List<OrderProduct> list = orderProductService.getListByOrderId(order_id);
    	Map<String, Object> map = new HashMap<>();
    	map.put("order", order);
    	map.put("orderProducts", list);
    	return map;
    }
   
   @GetMapping("/mainProduct/{order_id}")
   public Map<String, Object> getMainProduct(@PathVariable int order_id) {
	   String mainProduct = orderService.getMainProduct(order_id);
	   logger.info(mainProduct);
	   Map<String, Object> map = new HashMap<>();
	   map.put("mainProduct",mainProduct);
	   return map;
   }
}
