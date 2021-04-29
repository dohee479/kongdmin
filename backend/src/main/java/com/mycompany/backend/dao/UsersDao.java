package com.mycompany.backend.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycompany.backend.dto.Pager;
import com.mycompany.backend.dto.User;

@Mapper
public interface UsersDao {
   public List<User> selectByPage(Pager pager);
   public List<User> selectBySearch(Pager pager);
   public int count();
   public int countSearch(String keyword); //검색한 키워드를 포함한 컬럼 개수
   public String userAuthority(String user_id);
   public User selectById(String user_id);
   public int deleteById(String user_id);
   public Integer totalPay(String user_id);
   public int selectMemberCount();
}