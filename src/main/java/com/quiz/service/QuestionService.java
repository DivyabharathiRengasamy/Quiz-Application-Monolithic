package com.quiz.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quiz.model.Question;
import com.quiz.repository.QuestionRepository;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository repository;
    
    public Question saveQuestion(Question question) {
        return repository.save(question);
    }
    public Question getQuizById(Long id) {
    	return repository.findById(id).orElse(null);
    }
    public List<Question> getQuizByDiffcultyLevel(String diffcultyLevel) {
    	return repository.findByDifficultyLevel(diffcultyLevel);
    	
    }
    public Question updateQuizById(long id,String options,int optionNumber) {
    	
    Optional<Question>optional=	repository.findById(id);
    if(optional.isPresent()) {
    	Question question=optional.get();
    	switch (optionNumber) {
        case 1:
            question.setOption1(options);
            break;
        case 2:
            question.setOption2(options);
            break;
        case 3:
            question.setOption3(options);
            break;
        case 4:
            question.setOption4(options);
            break;
        default:
            throw new IllegalArgumentException("Invalid option number: " + optionNumber);
    }
    return repository.save(question);
    		
    }
    else {
    	return null;
    }
    }
    
    public void deleteQuizById(long id) {
    	
    	repository.deleteById(id);
    }
}


