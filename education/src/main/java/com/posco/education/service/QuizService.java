package com.posco.education.service;

import com.posco.education.domain.entity.Lecture;
import com.posco.education.domain.entity.Quiz;
import com.posco.education.repository.LectureRepository;
import com.posco.education.repository.PointRepository;
import com.posco.education.repository.QuizRepository;
import com.posco.education.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.List;

@Service
public class QuizService {
    private final UserRepository userRepository;
    private final LectureRepository lectureRepository;
    private final QuizRepository quizRepository;
    private final PointRepository pointRepository;

    @Autowired
    public QuizService(UserRepository userRepository, LectureRepository lectureRepository,QuizRepository quizRepository,PointRepository pointRepository) {
        this.userRepository = userRepository;
        this.lectureRepository = lectureRepository;
        this.quizRepository = quizRepository;
        this.pointRepository = pointRepository;
    }

    public Quiz randomQuizByTopic(String topic) {
        List<Quiz> quizesByTopic = quizRepository.findByTopic(topic);

        if (quizesByTopic.isEmpty()) {
            return null;
        }

        int randomIndex = new Random().nextInt(quizesByTopic.size());

        // Retrieve and return the randomly selected quiz
        return quizesByTopic.get(randomIndex);
    }
}
