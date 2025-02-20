package com.QuizService.repositories;

import com.QuizService.entities.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository   extends JpaRepository<Quiz,Long> {



}
