package com.quiz.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.quiz.model.Question;
import com.quiz.service.QuestionService;

@RestController
public class QuestionController {
    
    @Autowired
    private QuestionService questionService;

    @PostMapping("/save")
    public ResponseEntity<Question> saveQuiz(@RequestBody Question question) {
        Question savedQuestion = questionService.saveQuestion(question);
        return new ResponseEntity<>(savedQuestion, HttpStatus.CREATED);
    }
    

    @GetMapping("/getQuizById/{id}")
    public ResponseEntity<Question> getQuiz(@PathVariable long id) {
        Question question = questionService.getQuizById(id);
        if (question != null) {
            return ResponseEntity.ok(question);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/getQuizByDiffcultyLevel/{diffcultyLevel}")
    public List<Question>getQuizByDiffcultyLevel(@PathVariable String diffcultyLevel){
    	return questionService.getQuizByDiffcultyLevel(diffcultyLevel);
    }
    
    @PutMapping("/updateQuizById/{id}")
    	public  ResponseEntity<Question> updateQuizById
    	(@PathVariable long id, @RequestParam String option, @RequestParam int number) {
    	 try {
             Question updatedQuestion = questionService.updateQuizById(id, option, number);
             if (updatedQuestion != null) {
                 return ResponseEntity.ok(updatedQuestion);
             } else {
                 return ResponseEntity.notFound().build();
             }
         } catch (IllegalArgumentException e) {
             return ResponseEntity.badRequest().body(null);
         }
    }
    
    @DeleteMapping("/deleteQuizById/{id}")
    public ResponseEntity<Void> deleteQuizById(@PathVariable long id) {
        try {
            questionService.deleteQuizById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
