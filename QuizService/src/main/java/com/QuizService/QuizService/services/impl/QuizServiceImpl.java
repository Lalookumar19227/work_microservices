package com.QuizService.QuizService.services.impl;

import com.QuizService.QuizService.entities.Quiz;
import com.QuizService.QuizService.repositories.QuizRepository;
import com.QuizService.QuizService.services.QuestionClient;
import com.QuizService.QuizService.services.QuizService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class QuizServiceImpl  implements QuizService {

    private QuizRepository quizRepository;
    private  QuestionClient questionClient;

//    public QuizServiceImpl(QuizRepository quizRepository) {
//        this.quizRepository = quizRepository;
//    }


    public QuizServiceImpl(QuizRepository quizRepository, QuestionClient questionClient) {
        this.quizRepository = quizRepository;
        this.questionClient = questionClient;
    }

    @Override
    public Quiz add(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    @Override
    public List<Quiz> get() {

        List<Quiz> quizzes = quizRepository.findAll();

        List<Quiz> newQuizList = quizzes.stream().map(quiz -> {
            quiz.setQuestions(questionClient.getQuestionOfQuiz(quiz.getId()));
            return quiz;
        }).collect(Collectors.toList());

        return newQuizList;

    }

    @Override
    public Quiz get(Long id) {

        Quiz quiz = quizRepository.findById(id).orElseThrow(() -> new RuntimeException("Quiz not found"));
        quiz.setQuestions(questionClient.getQuestionOfQuiz(quiz.getId()));
        return quiz;
    }

//    private QuizRepository quizRepository;
//
//
//    private QuestionClient questionClient;
//
//    public QuizServiceImpl(QuizRepository quizRepository, QuestionClient questionClient) {
//        this.quizRepository = quizRepository;
//        this.questionClient = questionClient;
//    }
//
//    @Override
//    public Quiz add(Quiz quiz) {
//        return quizRepository.save(quiz);
//    }
//
//    @Override
//    public List<Quiz> get() {
//
//        List<Quiz> quizzes = quizRepository.findAll();
//
//        List<Quiz> newQuizList = quizzes.stream().map(quiz -> {
//            quiz.setQuestions(questionClient.getQuestionOfQuiz(quiz.getId()));
//            return quiz;
//        }).collect(Collectors.toList());
//
//        return newQuizList;
//
//    }
//
//    @Override
//    public Quiz get(Long id) {
//        Quiz quiz = quizRepository.findById(id).orElseThrow(() -> new RuntimeException("Quiz not found"));
//        quiz.setQuestions(questionClient.getQuestionOfQuiz(quiz.getId()));
//        return quiz;
//    }


//    private QuizRepository quizRepository;
//
//    private QuestionClient questionClient;
//
//    public QuizServiceImpl(QuizRepository quizRepository, QuestionClient questionClient) {
//        this.quizRepository = quizRepository;
//        this.questionClient = questionClient;
//    }
//
//    @Override
//    public Quiz add(Quiz quiz) {
//        return quizRepository.save(quiz);
//    }
//
//    @Override
//    public List<Quiz> get() {
//        List<Quiz> quizzes = quizRepository.findAll();
//
//        List<Quiz> newQuizList = quizzes.stream().map(quiz -> {
//            quiz.setQuestions(questionClient.getQuestionOfQuiz(quiz.getId()));
//            return quiz;
//        }).collect(Collectors.toList());
//
//        return newQuizList;
//    }
//
//    @Override
//    public Quiz get(Long id) {
//
//        Quiz quiz = quizRepository.findById(id).orElseThrow(() -> new RuntimeException("Quiz not found"));
//        quiz.setQuestions(questionClient.getQuestionOfQuiz(quiz.getId()));
//        return quiz;
//    }
}





