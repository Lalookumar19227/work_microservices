package com.QuizService.services;


import com.QuizService.entities.Question;
//import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


//@FeignClient(name = "QUESTION-SERVICE")
//@FeignClient(url = "http://localhost:8083",value = "Question-Client")

@FeignClient( name = "QUESTION-SERVICE")
public interface QuestionClient {


    @GetMapping("/question/quiz/{quizId}")
    List<Question> getQuestionOfQuiz(@PathVariable Long quizId);
}
