package com.QuizService.services;

import com.QuizService.entities.Quiz;

import java.util.List;

public interface QuizService {

    Quiz add(Quiz quiz);

    List<Quiz> get();

    Quiz get(Long id);
}
