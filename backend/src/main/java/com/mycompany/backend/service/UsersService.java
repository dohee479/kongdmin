package com.mycompany.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.backend.dao.UsersDao;
import com.mycompany.backend.dto.Pager;
import com.mycompany.backend.dto.User;

@Service
public class UsersService {
   @Autowired
   private UsersDao usersDao;

   public int getCount() {
      return usersDao.count();
   }
   
   public int getCountSearch(String keyword) {
	      return usersDao.countSearch(keyword);
   }
   
   public String getAuthority(String user_id) {
	   return usersDao.userAuthority(user_id);
   }
   
   public List<User> getList(Pager pager) {
      return usersDao.selectByPage(pager);
   }
   
   public List<User> getSearchList(Pager pager) {
	      return usersDao.selectBySearch(pager);
	   }
   
   public User getUser(String user_id) {
	  return usersDao.selectById(user_id);
   }

   public int delete(String user_id) {
      return usersDao.deleteById(user_id);
   }
   
   public Integer totalPay(String user_id) {
	   return usersDao.totalPay(user_id);
   }
}