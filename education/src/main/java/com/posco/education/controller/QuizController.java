package com.posco.education.controller;


import com.posco.education.domain.entity.Lecture;
import com.posco.education.domain.entity.Quiz;
import com.posco.education.repository.ReviewRepository;
import com.posco.education.service.LectureService;
import com.posco.education.service.QuizService;
import com.posco.education.service.ReviewService;
import com.posco.education.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path="/quiz")
public class QuizController {

    private final QuizService quizService;

    @Autowired
    public QuizController(QuizService quizService) {
        this.quizService = quizService;

    }

    @GetMapping("/{topic}/topic-random-quiz")
    public ResponseEntity<Quiz> randomQuizByTopic(@PathVariable String topic) {
        Quiz randomQuiz = quizService.randomQuizByTopic(topic);
        return new ResponseEntity<>(randomQuiz, HttpStatus.OK);
    }

}
