package com.posco.education.controller;


import com.posco.education.domain.dto.ReviewRequest;
import com.posco.education.domain.dto.UserMyLectureRequest;
import com.posco.education.domain.entity.Review;
import com.posco.education.repository.ReviewRepository;
import com.posco.education.service.LectureService;
import com.posco.education.service.ReviewService;
import com.posco.education.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/review")
public class ReviewController {

    private final UserService userService;
    private final LectureService lectureService;
    private final ReviewService reviewService;
    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewController(UserService userService, LectureService lectureService, ReviewService reviewService,ReviewRepository reviewRepository) {
        this.userService = userService;
        this.lectureService = lectureService;
        this.reviewService = reviewService;
        this.reviewRepository = reviewRepository;
    }

    @PostMapping("/create-review")
    public ResponseEntity<String> createReview(@RequestBody ReviewRequest reviewRequest) {
        reviewService.createReview(reviewRequest.getUserId(), reviewRequest.getLectureId(), reviewRequest.getTitle(), reviewRequest.getContent());
        return new ResponseEntity<>("success", HttpStatus.OK);
    }
    @PutMapping("/delete-review")
    public ResponseEntity<String> deleteReview(@RequestBody ReviewRequest reviewRequest) {
        reviewService.deleteReview(reviewRequest.getReviewId(),reviewRequest.getUserId(), reviewRequest.getLectureId());
        return new ResponseEntity<>("success", HttpStatus.OK);
    }
    @PutMapping("/update-review")
    public ResponseEntity<String> updateReview(@RequestBody ReviewRequest reviewRequest) {
        reviewService.updateReview(reviewRequest.getReviewId(), reviewRequest.getTitle(), reviewRequest.getContent());
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @GetMapping("/all-reviews")
    public ResponseEntity<List<Review>> reviewList() {
        List<Review> reviews = reviewRepository.findAll();
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @GetMapping("/{lectureId}/lecture-reviews")
    public ResponseEntity<List<Review>> reviewByLecture(@PathVariable Integer lectureId) {
        List<Review> lectureReviews = reviewService.reviewByLecture(lectureId);
        return new ResponseEntity<>(lectureReviews, HttpStatus.OK);
    }

    @GetMapping("/{lectureId}/lecture-reviews-byLike")
    public ResponseEntity<List<Review>> reviewByLectureOrderByLike(@PathVariable Integer lectureId) {
        List<Review> lectureReviewsOrderByLike = reviewService.reviewByLectureOrderByLike(lectureId);
        return new ResponseEntity<>(lectureReviewsOrderByLike, HttpStatus.OK);
    }

    @GetMapping("/{userId}/user-reviews")
    public ResponseEntity<List<Review>> reviewByUser(@PathVariable String userId) {
        List<Review> userReviews = reviewService.reviewByUser(userId);
        return new ResponseEntity<>(userReviews, HttpStatus.OK);
    }

    @PutMapping("/like-review")
    public ResponseEntity<String> likeReview(@RequestBody ReviewRequest reviewRequest) {
        reviewService.likeReview(reviewRequest.getReviewId());
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @PutMapping("/dislike-review")
    public ResponseEntity<String> dislikeReview(@RequestBody ReviewRequest reviewRequest) {
        reviewService.dislikeReview(reviewRequest.getReviewId());
        return new ResponseEntity<>("success", HttpStatus.OK);
    }
}
