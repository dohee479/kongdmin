package com.mycompany.backend.controller;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mycompany.backend.dto.Pager;
import com.mycompany.backend.dto.Product;
import com.mycompany.backend.service.ProductsService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductsService productsService;
	
	@GetMapping("")
	public Map<String, Object> list(String keyword, String kcontent, @RequestParam(defaultValue="1") int pageNo, String sort) {
		HashMap<String, Object> searchProduct = new HashMap<>();
		searchProduct.put("keyword", keyword);
		searchProduct.put("kcontent", kcontent);
		searchProduct.put("sort", sort);
		int totalRows = productsService.getCount(searchProduct);
		Pager pager = new Pager(5, 5, totalRows, pageNo);
		searchProduct.put("startRowNo", pager.getStartRowNo());
		searchProduct.put("endRowNo", pager.getEndRowNo());
		List<Product> productList = productsService.getList(searchProduct);
		Map<String, Object> productMap = new HashMap<>();
		productMap.put("pager", pager);
		productMap.put("productList", productList);
		return productMap;
	}
	
	@PostMapping("")
	public void create(Product product) {
		System.out.println(product.getProduct_country());
		MultipartFile product_attach = product.getProduct_attach();
		MultipartFile product_detail_attach = product.getProduct_detail_attach();
		// 파일의 original name
		product.setProduct_attachoname(product_attach.getOriginalFilename());
		// 저장 파일 이름(날짜를 더해서 파일명을 지정)
		product.setProduct_attachsname(new Date().getTime() + "-" + product_attach.getOriginalFilename());
		// 파일의 타입
		product.setProduct_attachtype(product_attach.getContentType());
		
		product.setProduct_detail_attachoname(product_detail_attach.getOriginalFilename());
		product.setProduct_detail_attachsname(new Date().getTime() + "-" + product_detail_attach.getOriginalFilename());
		product.setProduct_detail_attachtype(product_detail_attach.getContentType());
		
        try {
            File file = new File("D:/kong/" + product.getProduct_attachsname());
            product_attach.transferTo(file);
            
            File file2 = new File("D:/kong/" + product.getProduct_detail_attachsname());
            product_detail_attach.transferTo(file2);
         } catch (Exception e) {
            e.printStackTrace();
         }
        
        productsService.create(product);
        
	}
	
	@GetMapping("/product-attach/{product_id}")
	public void ProductImg(@PathVariable int product_id, HttpServletResponse response) {
		productsService.getProductImg(product_id, response);
	}
	
	@DeleteMapping("/delete")
	public void delete(@RequestParam List<Integer> deleteId) {
		System.out.println(deleteId);
		productsService.delete(deleteId);
	}
	
}
