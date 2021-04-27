package com.mycompany.backend.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycompany.backend.dto.Pager;
import com.mycompany.backend.dto.User;

@Mapper
public interface UsersDao {
   public List<User> selectByPage(Pager pager);
   public int count();
   public User selectById(String user_id);
   public int deleteById(String user_id);
   public Integer totalPay(String user_id);
}