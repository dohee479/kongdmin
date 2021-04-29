package com.mycompany.backend.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycompany.backend.dto.Product;

@Mapper
public interface ProductsDao {
	public List<Product> selectByCountry(String product_country);
	public List<Product> selectByTaste(String product_taste);
	public int selectCount();
}
