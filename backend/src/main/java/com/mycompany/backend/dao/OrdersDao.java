package com.mycompany.backend.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mycompany.backend.dto.MonthlyData;
import com.mycompany.backend.dto.Order;
import com.mycompany.backend.dto.OrderCount;
import com.mycompany.backend.dto.Pager;



@Mapper
public interface OrdersDao {
	public List<Order> selectByPage(@Param("pager")Pager pager, 
									@Param("state")int state, 
									@Param("search_user_id")String search_user_id,
									@Param("fromPrice")int fromPrice,
									@Param("toPrice")int toPrice,
									@Param("fromDate")String fromDate,
									@Param("toDate")String toDate,
									@Param("sortDsc")String sortDsc);
	
	public int count(@Param("state")int state, 
					 @Param("search_user_id")String search_user_id,
					 @Param("fromPrice")int fromPrice,
					 @Param("toPrice")int toPrice,
					 @Param("fromDate")String fromDate,
					 @Param("toDate")String toDate
					);
	
	public Order selectByOrderId(int order_id);
	
	public int selectStateCountByState(@Param("state")int state, 
									   @Param("search_user_id")String search_user_id,
									   @Param("fromPrice")int fromPrice,
									   @Param("toPrice")int toPrice,
									   @Param("fromDate")String fromDate,
									   @Param("toDate")String toDate);
	
	public String selectMainProductByOrderId(int order_id);
	public List<MonthlyData> selectMonthPrice();
	public List<MonthlyData> selectMonthCount();
	public List<OrderCount> selectCountryCount();
	public List<OrderCount> selectTasteCount();
	public int selectOrderCount();
	public List<MonthlyData> selectDatePrice(String order_month);
	public List<Order> selectByDate(String order_date);
	public void changeState(@Param("order_id")int order_id, @Param("changeState")int changeState);
	public int getProductCount(int order_id);
	public void updateOrder(Order order);
}

