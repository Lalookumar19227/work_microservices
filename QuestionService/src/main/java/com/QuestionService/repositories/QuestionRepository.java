package com.QuestionService.repositories;

import com.QuestionService.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//import java.util.Optional;


public interface QuestionRepository extends JpaRepository<Question,Long> {

    List<Question> findByQuizId(Long quizId);

//    @Query(name = "Question.findBySomething")
//    List<Question> findByQuizId(Long quizId);


}
