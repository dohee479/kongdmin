package com.mycompany.backend.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycompany.backend.dto.MonthlyData;
import com.mycompany.backend.dto.OrderCount;



@Mapper
public interface OrdersDao {
	public List<MonthlyData> selectMonthPrice();
	public List<MonthlyData> selectMonthCount();
	public List<OrderCount> selectCountryCount();
	public List<OrderCount> selectTasteCount();
}

