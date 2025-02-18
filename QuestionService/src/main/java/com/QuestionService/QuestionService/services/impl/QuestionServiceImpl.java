package com.QuestionService.QuestionService.services.impl;


import com.QuestionService.QuestionService.entities.Question;
import com.QuestionService.QuestionService.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

   public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }


    @Override
    public Question create(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public List<Question> get() {
        return questionRepository.findAll();
    }

    @Override
    public Question getOne(Long id) {
        return questionRepository.findById(id).orElseThrow(() -> new RuntimeException("Question not found !!"));
    }

    @Override
    public List<Question> getQuestionsOfQuiz(Long quizId) {
        return questionRepository.findByQuizId(quizId);
    }

    ;

//    @Override
//   public List<Question> getQuestionsOfQuiz(Long quizId) {
//
//        return questionRepository.findByQuizId(quizId);
//    }


//    @Autowired
//    private QuestionRepository questionRepository;
//
//    public QuestionServiceImpl(QuestionRepository questionRepository) {
//        this.questionRepository = questionRepository;
//    }
//
//    @Override
//    public Question create(Question question) {
//        return questionRepository.save(question);
//    }
//
//    @Override
//    public List<Question> get() {
//        return questionRepository.findAll();
//    }
//
//    @Override
//    public Question getOne(Long id) {
//        return questionRepository.findById(id).orElseThrow(() -> new RuntimeException("Question not found !!"));
//    }
//
//    @Override
//    public List<Question> getQuestionsOfQuiz(Long questionId) {
//
//        return questionRepository.findByQuizId(questionId);
//    }
//

}
