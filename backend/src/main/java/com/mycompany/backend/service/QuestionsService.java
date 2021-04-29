package com.mycompany.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.backend.dao.QuestionsDao;
import com.mycompany.backend.dto.Pager;
import com.mycompany.backend.dto.Question;

@Service
public class QuestionsService {
   @Autowired
   private QuestionsDao questionsDao;
   
   //데이터 개수
   public int getCount() {
      return questionsDao.count();
   }

   //페이지 단위 리스트 가져오기
   public List<Question> getList(Pager pager) {
      return questionsDao.selectByPage(pager);
   }
   
   //질문번호에 따른 목록
   public Question getQuestion(int question_id) {
      return questionsDao.selectById(question_id);
   }
   
   //질문 수정
   public int update(Question question) {
	  return questionsDao.update(question);
   }
   
   //삭제
   public int delete(int question_id) {
      return questionsDao.deleteById(question_id);
   }
   
}