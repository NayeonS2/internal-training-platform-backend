package com.posco.education.repository;

import com.posco.education.domain.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {

    Optional<Review> findByReviewId(Integer reviewId);

}