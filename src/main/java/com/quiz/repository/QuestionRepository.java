package com.quiz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.quiz.model.Question;



public interface QuestionRepository extends JpaRepository<Question, Long>{

	List<Question> findByDifficultyLevel(String difficultyLevel);
	

    @Query(value = "SELECT * FROM Question ORDER BY RAND() LIMIT 5", nativeQuery = true)
    List<Question> findFiveRandomQuestions();


	
	

}
