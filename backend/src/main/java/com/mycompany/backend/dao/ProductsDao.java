package com.mycompany.backend.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycompany.backend.dto.Pager;
import com.mycompany.backend.dto.Product;

@Mapper
public interface ProductsDao {
	public int count(HashMap<String, Object> searchProduct);
	public void create(Product product);
	public List<Product> list(HashMap<String, Object> searchProduct);
	public Product getProduct(int product_id);
	public void delete(List<Integer> deleteId);
}
