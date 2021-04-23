package com.mycompany.backend.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mycompany.backend.dto.Order;
import com.mycompany.backend.dto.Pager;

@Mapper
public interface OrderDao{
	public List<Order> selectByPage(@Param("pager")Pager pager, @Param("state")int state, @Param("search_user_id")String search_user_id);
	public int count(@Param("state")int state, @Param("search_user_id")String search_user_id);
	public Order selectByOrderId(int order_id);
	public int selectStateCountByState(int state);
	public String selectMainProductByOrderId(int order_id);
}

