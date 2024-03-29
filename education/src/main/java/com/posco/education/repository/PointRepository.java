package com.posco.education.repository;

import com.posco.education.domain.entity.Lecture;
import com.posco.education.domain.entity.Point;
import com.posco.education.domain.entity.Quiz;
import com.posco.education.domain.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PointRepository extends JpaRepository<Point, Integer> {

//    Optional<Point> findByPointId(Integer pointId);

}