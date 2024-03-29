package com.posco.education.repository;

import com.posco.education.domain.entity.Lecture;
import com.posco.education.domain.entity.Quiz;
import com.posco.education.domain.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Integer> {

//    Optional<Quiz> findByQuizId(Integer quizId);

    List<Quiz> findByTopic(String topic);

}