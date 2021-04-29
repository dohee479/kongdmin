package com.mycompany.backend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.backend.dto.Pager;
import com.mycompany.backend.dto.User;
import com.mycompany.backend.service.UsersService;

@RestController
@RequestMapping("/user")
public class UsersController {
	private final Logger logger=LoggerFactory.getLogger(UsersController.class);
	
	@Autowired
	private UsersService usersService;
	
	// 페이지 단위로 유저의 목록 보기
	@GetMapping("")
	public Map<String, Object> list(int pageNo,String keyword) {
		//@RequestParam: 단일파라미터를 전달받을때사용
		logger.info("list컨트롤러 진입, pageNo: "+pageNo);
		logger.info("keyword값: "+keyword);
		int totalRows;
		List<User> list;
		Pager pager;
		
		if(keyword==null||keyword.length()==0) { //키워드가 안들어갔을때
			logger.info("keyword값이 null일때이벤트: "+keyword);
			totalRows=usersService.getCount();
			pager=new Pager(5,5,totalRows,pageNo);
			list=usersService.getList(pager);
		}else { //키워드가 입력되었거나, 바뀔때
			logger.info("--keyword값 있을때의 이벤트--");
			totalRows=usersService.getCountSearch(keyword);
			logger.info("keyword값 있을때의 컬럼개수(totalRows): "+totalRows);
			if(totalRows==0) {
				totalRows=1;
			}
			pager=new Pager(5,5,totalRows,pageNo);
			pager.setKeyword(keyword);
			list=usersService.getSearchList(pager);
		}
		
		Map<String, Object> map=new HashMap<>();
		map.put( "pager" , pager);
		map.put( "users", list);
		return map;
	}
	
	// 유저정보읽기(상세보기)
	@GetMapping("/{user_id}") 
	public User read(@PathVariable String user_id) { //{}사이에있는 값을 매개변수로 (URL 경로에 변수를 넣어줌)
		   User user=usersService.getUser(user_id);
		   int totalPay;
		   if(usersService.totalPay(user_id)==null) {
			   totalPay=0;
		   }else {
			   totalPay = usersService.totalPay(user_id);
		   }
		   user.setTotal_pay(totalPay);
		   return user;
	}
	
	// 유저정보삭제
	@DeleteMapping("/{user_id}") //삭제 어노테이션
	public void delete(@PathVariable String user_id) {
		usersService.delete(user_id);
	}
}