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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.backend.dto.Pager;
import com.mycompany.backend.dto.Question;
import com.mycompany.backend.service.QuestionsService;

@RestController
@RequestMapping("/question")
public class QuestionsController {
	private final Logger logger=LoggerFactory.getLogger(QuestionsController.class);
	
	@Autowired
	private QuestionsService questionsService;
	
	// 페이지 단위로 유저의 목록 보기
	@GetMapping("")
	public Map<String, Object> list(@RequestParam(defaultValue="1") int pageNo) {
		//@RequestParam: 단일파라미터를 전달받을때사용
		int totalRows=questionsService.getCount();
		Pager pager=new Pager(5,5,totalRows,pageNo);
		List<Question> list=questionsService.getList(pager);
		Map<String, Object> map=new HashMap<>();
		map.put( "pager" , pager);
		map.put( "questions", list);
		return map;
	}
	
	// 유저정보읽기(상세보기)
	@GetMapping("/{question_id}") 
	public Question read(@PathVariable int question_id) { //{}사이에있는 값을 매개변수로 (URL 경로에 변수를 넣어줌)
		   Question question=questionsService.getQuestion(question_id);
		   return question;
	}
	
	// 질문 수정(기본:null)
	@PutMapping("")
	public Question updateQuestion(@RequestBody Question question){
		questionsService.update(question);
		return question;
	}
	
	// 유저정보삭제
	@DeleteMapping("/{question_id}") //삭제 어노테이션
	public void delete(@PathVariable int question_id) {
		questionsService.delete(question_id);
	}
}