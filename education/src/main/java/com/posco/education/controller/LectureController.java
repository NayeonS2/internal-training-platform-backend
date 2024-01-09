package com.posco.education.controller;


import com.posco.education.domain.dto.UserMyLectureRequest;
import com.posco.education.domain.entity.Lecture;
import com.posco.education.domain.entity.Review;
import com.posco.education.repository.LectureRepository;
import com.posco.education.service.LectureService;
import com.posco.education.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/lecture")
public class LectureController {

    private final UserService userService;
    private final LectureService lectureService;
    private final LectureRepository lectureRepository;

    @Autowired
    public LectureController(UserService userService, LectureService lectureService,LectureRepository lectureRepository) {
        this.userService = userService;
        this.lectureService = lectureService;
        this.lectureRepository = lectureRepository;

    }


    @PostMapping("/add-mylecture")
    public ResponseEntity<String> addLectureToMyLecture(@RequestBody UserMyLectureRequest userMyLectureRequest) {
        lectureService.addLectureToMyLecture(userMyLectureRequest.getUserId(), userMyLectureRequest.getLectureId());
        return new ResponseEntity<>("success",HttpStatus.OK);
    }
    @PutMapping("/delete-mylecture")
    public ResponseEntity<String> deleteLectureFromMyLecture(@RequestBody UserMyLectureRequest userMyLectureRequest) {
        lectureService.deleteLectureFromMyLecture(userMyLectureRequest.getUserId(), userMyLectureRequest.getLectureId());
        return new ResponseEntity<>("success",HttpStatus.OK);
    }

    @GetMapping("/all-lectures")
    public ResponseEntity<List<Lecture>> lectureList() {
        List<Lecture> lectures = lectureRepository.findAll();
        return new ResponseEntity<>(lectures, HttpStatus.OK);
    }

    @GetMapping("/all-lectures-byLike")
    public ResponseEntity<List<Lecture>> lectureListByLike() {
        List<Lecture> lecturesByLike = lectureRepository.findAllByOrderByLikeCntDesc();
        return new ResponseEntity<>(lecturesByLike, HttpStatus.OK);
    }

    @GetMapping("/{userId}/user-lectures")
    public ResponseEntity<List<Lecture>> lectureByUser(@PathVariable String userId) {
        List<Lecture> userLectures = lectureService.lectureByUser(userId);
        return new ResponseEntity<>(userLectures, HttpStatus.OK);
    }

    @GetMapping("/{topic}/topic-lectures")
    public ResponseEntity<List<Lecture>> lectureByTopic(@PathVariable String topic) {
        List<Lecture> lecturesByTopic = lectureService.lectureByTopic(topic);
        return new ResponseEntity<>(lecturesByTopic, HttpStatus.OK);
    }

    @GetMapping("/{topic}/topic-lectures-byLike")
    public ResponseEntity<List<Lecture>> lectureByTopicOrderByLike(@PathVariable String topic) {
        List<Lecture> lecturesByTopicOrderByLike = lectureService.lectureByTopicOrderByLike(topic);
        return new ResponseEntity<>(lecturesByTopicOrderByLike, HttpStatus.OK);
    }

    @GetMapping("/{userId}/{topic}/user-topic-lectures")
    public ResponseEntity<List<Lecture>> lectureByUserAndTopic(@PathVariable String userId, @PathVariable String topic) {
        List<Lecture> lecturesByUserAndTopic = lectureService.lectureByUserAndTopic(userId,topic);
        return new ResponseEntity<>(lecturesByUserAndTopic, HttpStatus.OK);
    }
}
