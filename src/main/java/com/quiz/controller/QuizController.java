package com.quiz.controller;


import com.quiz.dto.*;
import com.quiz.model.Question;
import com.quiz.model.Quiz;
import com.quiz.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quizzes")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @PostMapping("/create")
    public Quiz createQuiz(@RequestParam String title) {
        return quizService.createQuiz(title);
    }

    @GetMapping("/getAll")
    public List<Quiz> getAllQuizzes() {
        return quizService.getAllQuizzes();
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<List<QuestionDTO>> getQuizQuestion(@PathVariable Long id ){
    return	quizService.getQuizQuestions(id);
    	
    }
    @GetMapping("/submit/{id}")
    public ResponseEntity<Integer>submit(@PathVariable Long id,@RequestBody List<AnswerDTO>response){
		return quizService.calculateResult(id,response);
    	
    	
    }
    
    @PutMapping("/update/{id}")
    public Quiz updateQuiz(@PathVariable Long id, @RequestParam String title) {
        return quizService.updateQuizById(id, title);
    }
    
    @DeleteMapping("/delete/{id}")
    public void deleteQuiz(@PathVariable Long id) {
        quizService.deleteQuizById(id);
    }
}

