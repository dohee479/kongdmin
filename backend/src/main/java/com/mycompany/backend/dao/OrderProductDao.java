package com.mycompany.backend.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycompany.backend.dto.OrderProduct;

@Mapper
public interface OrderProductDao {
	public List<OrderProduct> selectAll(int orders_order_id);
}
