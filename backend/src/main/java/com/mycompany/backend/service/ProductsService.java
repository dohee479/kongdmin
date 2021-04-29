package com.mycompany.backend.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.backend.dao.ProductsDao;
import com.mycompany.backend.dto.Product;


@Service
public class ProductsService {
   @Autowired
   private ProductsDao productsDao;
   
   public List<Product> getProductByCountry(String product_country) {
	   return productsDao.selectByCountry(product_country);
	   
   }
   public List<Product> getProductByTaste(String product_taste) {
	   return productsDao.selectByTaste(product_taste);
   }
   public int getCount() {
	   return productsDao.selectCount();
   }

}