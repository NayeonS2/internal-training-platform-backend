package com.posco.education.service;

import com.posco.education.domain.entity.Lecture;
import com.posco.education.domain.entity.Review;
import com.posco.education.domain.entity.User;
import com.posco.education.repository.LectureRepository;
import com.posco.education.repository.ReviewRepository;
import com.posco.education.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class ReviewService {

    private final UserRepository userRepository;
    private final LectureRepository lectureRepository;
    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(UserRepository userRepository, LectureRepository lectureRepository, ReviewRepository reviewRepository) {
        this.userRepository = userRepository;
        this.lectureRepository = lectureRepository;
        this.reviewRepository = reviewRepository;
    }

    @Transactional
    public void createReview(String userId, Integer lectureId, String title, String content) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        Lecture lecture = lectureRepository.findById(lectureId)
                .orElseThrow(() -> new RuntimeException("Lecture not found with ID: " + lectureId));

        Review review = new Review(null, null, null, title, content, 0);

        reviewRepository.save(review);

        user.getReviews().add(review);
        lecture.getReviews().add(review);

        userRepository.save(user);
        lectureRepository.save(lecture);
    }

    @Transactional
    public void updateReview(Integer reviewId, String title, String content) {

        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("Review not found with ID: " + reviewId));

        review.updateReview(review.getUser(), review.getLecture(), title, content, review.getLikeCnt());

        reviewRepository.save(review);

    }

    @Transactional
    public void deleteReview(Integer reviewId, String userId, Integer lectureId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        Lecture lecture = lectureRepository.findById(lectureId)
                .orElseThrow(() -> new RuntimeException("Lecture not found with ID: " + lectureId));

        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("Review not found with ID: " + reviewId));

        user.getReviews().remove(review);
        lecture.getReviews().remove(review);

        review.deleteReview(null,null);

        reviewRepository.save(review);


        userRepository.save(user);
        lectureRepository.save(lecture);
    }


    public List<Review> reviewByLecture(Integer lectureId) {
        Lecture lecture = lectureRepository.findById(lectureId)
                .orElseThrow(() -> new RuntimeException("Lecture not found with ID: " + lectureId));

        List<Review> lectureReviews = lecture.getReviews();

        return lectureReviews;
    }

    public List<Review> reviewByLectureOrderByLike(Integer lectureId) {
        Lecture lecture = lectureRepository.findById(lectureId)
                .orElseThrow(() -> new RuntimeException("Lecture not found with ID: " + lectureId));

        List<Review> lectureReviewsOrderByLike = lecture.getReviews();

        lectureReviewsOrderByLike.sort(Comparator.comparingInt(Review::getLikeCnt).reversed());

        return lectureReviewsOrderByLike;
    }

    public List<Review> reviewByUser(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        List<Review> userReviews = user.getReviews();

        return userReviews;
    }

    @Transactional
    public void likeReview(Integer reviewId) {

        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("Review not found with ID: " + reviewId));

        review.updateReview(review.getUser(), review.getLecture(), review.getTitle(), review.getContent(), review.getLikeCnt()+1);

        reviewRepository.save(review);

    }

    @Transactional
    public void dislikeReview(Integer reviewId) {

        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("Review not found with ID: " + reviewId));

        review.updateReview(review.getUser(), review.getLecture(), review.getTitle(), review.getContent(), review.getLikeCnt()-1);

        reviewRepository.save(review);

    }
}
