package com.quiz.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.quiz.dto.*;
import com.quiz.model.*;
import com.quiz.repository.QuestionRepository;
import com.quiz.repository.QuizRepository;
@Service
public class QuizService {
	@Autowired
	QuestionRepository questionRepository;
	
	
	@Autowired
	QuizRepository quizRepository;
	
	public Quiz createQuiz(String title) {
		
		List<Question>getQuestion=questionRepository.findFiveRandomQuestions();
		Quiz quiz=new Quiz();
		quiz.setTitle(title);
		quiz.setQuestions(getQuestion);
		return quizRepository.save(quiz);
		
	}
	public Quiz updateQuizById(Long id, String title) {
	    Optional<Quiz> optional = quizRepository.findById(id);
	    if (optional.isPresent()) {
	        Quiz quiz = optional.get();
	        quiz.setTitle(title);
	        return quizRepository.save(quiz);
	    } else {
	        return null; // or throw an exception if preferred
	    }
	}

	public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }
	 public void deleteQuizById(Long id) {
	        quizRepository.deleteById(id);
	    }
	public ResponseEntity<List<QuestionDTO>> getQuizQuestions(Long id) {
	Optional<Quiz>quiz=	quizRepository.findById(id);
	List<Question>questionFromDB=quiz.get().getQuestions();
	List<QuestionDTO>questionDTOs=new ArrayList();
	for(Question question : questionFromDB) {
	QuestionDTO dto=new QuestionDTO(question.getId(), question.getTitle(),
			question.getQuestion(), question.getOption1(), question.getOption2(), 
			question.getOption3(), question.getOption4());
	questionDTOs.add(dto);
	
	}
	return new ResponseEntity<List<QuestionDTO>>(questionDTOs, HttpStatus.OK);
	}
	public ResponseEntity<Integer> calculateResult(Long id, List<AnswerDTO> response){
		
	Quiz quiz=quizRepository.findById(id).get();
	List<Question> question =quiz.getQuestions();
	int score =0;
	int i =0;
	for(AnswerDTO answerDTO: response) {
		if(answerDTO.getResponse().equalsIgnoreCase(question.get(i).getRightAnswer())){
			 score++;
			 i++;
		}
	}
	return new ResponseEntity<Integer>(score, HttpStatus.OK);
	
	}

		
	
		
	}


