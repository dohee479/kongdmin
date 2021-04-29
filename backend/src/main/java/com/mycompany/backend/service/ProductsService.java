package com.mycompany.backend.service;


import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import com.mycompany.backend.dao.ProductsDao;
import com.mycompany.backend.dto.Pager;
import com.mycompany.backend.dto.Product;

@Service
public class ProductsService {
	
	@Autowired
	private ProductsDao productsDao;
	
	public int getCount(HashMap<String, Object> searchProduct) {
		return productsDao.count(searchProduct);
	}
	
	public void create(Product product) {
		productsDao.create(product);
	}

	public List<Product> getList(HashMap<String, Object> searchProduct) {
		return productsDao.list(searchProduct);
	}

	public void getProductImg(int product_id, HttpServletResponse response) {
		try {
			Product product = productsDao.getProduct(product_id);
			String productAttachoname = product.getProduct_attachoname();
			if (productAttachoname == null) {
				return;
			}
			productAttachoname = new String(productAttachoname.getBytes("UTF-8"),"ISO-8859-1");
			String productAttachsname = product.getProduct_attachsname();
			String productAttachspath = "D:/kong/" + productAttachsname;
			String productAttachtype = product.getProduct_attachtype();
			
			response.setHeader("Content-Disposition", "attachment; filename=\""+ productAttachoname +"\";");
			response.setContentType(productAttachtype);
			
            InputStream is = new FileInputStream(productAttachspath);
            OutputStream os = response.getOutputStream();
            FileCopyUtils.copy(is, os);
            is.close();
            os.flush();
            os.close();
		}  catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void delete(List<Integer> deleteId) {
		// TODO Auto-generated method stub
		productsDao.delete(deleteId);
		
	}
}
