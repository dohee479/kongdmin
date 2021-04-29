package com.mycompany.backend.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycompany.backend.dto.Pager;
import com.mycompany.backend.dto.Question;

@Mapper
public interface QuestionsDao {
   public List<Question> selectByPage(Pager pager);
   public int count();
   public Question selectById(int question_id);
   public int update(Question question);
   public int deleteById(int question_id);	
}