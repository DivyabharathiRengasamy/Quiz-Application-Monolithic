package com.quiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quiz.model.Quiz;
import java.util.*;
import com.quiz.model.*;

public interface QuizRepository extends JpaRepository<Quiz, Long>{

	

}
