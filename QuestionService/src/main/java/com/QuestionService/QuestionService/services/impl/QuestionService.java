package com.QuestionService.QuestionService.services.impl;

import com.QuestionService.QuestionService.entities.Question;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface QuestionService {

    Question create(Question question);
    List<Question> get();
    Question getOne(Long id);

    List<Question> getQuestionsOfQuiz(Long quizId);




}
