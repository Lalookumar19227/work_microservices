package com.QuestionService.QuestionService.entities;



import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.aspectj.bridge.IMessage;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long questionId;


    private  String question;

    private  Long quizId;

//    public Long getQuestionId() {
//        return questionId;
//    }
//
//    public void setQuestionId(Long questionId) {
//        this.questionId = questionId;
//    }
//
//    public String getQuestion() {
//        return question;
//    }
//
//    public void setQuestion(String question) {
//        this.question = question;
//    }
//
//    public Long getQuizId() {
//        return quizId;
//    }
//
//    public void setQuizId(Long quizId) {
//        this.quizId = quizId;
//    }


}
