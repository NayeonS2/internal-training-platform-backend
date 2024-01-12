package com.posco.education.controller;


import com.posco.education.domain.dto.UserMyLectureRequest;
import com.posco.education.domain.entity.Lecture;
import com.posco.education.domain.entity.Review;
import com.posco.education.repository.LectureRepository;
import com.posco.education.service.LectureService;
import com.posco.education.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "내 강의실에 강의 추가")
    public ResponseEntity<String> addLectureToMyLecture(@RequestBody UserMyLectureRequest userMyLectureRequest) {
        lectureService.addLectureToMyLecture(userMyLectureRequest.getUserId(), userMyLectureRequest.getLectureId());
        return new ResponseEntity<>("success",HttpStatus.OK);
    }
    @PutMapping("/delete-mylecture")
    @Operation(summary = "내 강의실에 강의 삭제")
    public ResponseEntity<String> deleteLectureFromMyLecture(@RequestBody UserMyLectureRequest userMyLectureRequest) {
        lectureService.deleteLectureFromMyLecture(userMyLectureRequest.getUserId(), userMyLectureRequest.getLectureId());
        return new ResponseEntity<>("success",HttpStatus.OK);
    }

    @GetMapping("/all-lectures")
    @Operation(summary = "전체 강의 리스트")
    public ResponseEntity<List<Lecture>> lectureList() {
        List<Lecture> lectures = lectureRepository.findAll();
        return new ResponseEntity<>(lectures, HttpStatus.OK);
    }

    @GetMapping("/{lectureId}/lecture-byId")
    @Operation(summary = "강의 아이디별 강의")
    public ResponseEntity<Lecture> lectureById(@PathVariable Integer lectureId) {
        Lecture lecture = lectureRepository.findByLectureId(lectureId);
        return new ResponseEntity<>(lecture, HttpStatus.OK);
    }

    @GetMapping("/all-lectures-byLike")
    @Operation(summary = "전체 강의 리스트 (담긴 수 내림차순)")
    public ResponseEntity<List<Lecture>> lectureListByLike() {
        List<Lecture> lecturesByLike = lectureRepository.findAllByOrderByLikeCntDesc();
        return new ResponseEntity<>(lecturesByLike, HttpStatus.OK);
    }

    @GetMapping("/{userId}/user-lectures")
    @Operation(summary = "유저별 내 강의실 강의 리스트")
    public ResponseEntity<List<Lecture>> lectureByUser(@PathVariable String userId) {
        List<Lecture> userLectures = lectureService.lectureByUser(userId);
        return new ResponseEntity<>(userLectures, HttpStatus.OK);
    }

    @GetMapping("/{topic}/topic-lectures")
    @Operation(summary = "주제별 강의 리스트")
    public ResponseEntity<List<Lecture>> lectureByTopic(@PathVariable String topic) {
        List<Lecture> lecturesByTopic = lectureService.lectureByTopic(topic);
        return new ResponseEntity<>(lecturesByTopic, HttpStatus.OK);
    }

    @GetMapping("/{topic}/topic-lectures-byLike")
    @Operation(summary = "주제별 강의 리스트 (담긴 수 내림차순)")
    public ResponseEntity<List<Lecture>> lectureByTopicOrderByLike(@PathVariable String topic) {
        List<Lecture> lecturesByTopicOrderByLike = lectureService.lectureByTopicOrderByLike(topic);
        return new ResponseEntity<>(lecturesByTopicOrderByLike, HttpStatus.OK);
    }

    @GetMapping("/{userId}/{topic}/user-topic-lectures")
    @Operation(summary = "유저별 내 강의실 강의 리스트 (주제별 분류)")
    public ResponseEntity<List<Lecture>> lectureByUserAndTopic(@PathVariable String userId, @PathVariable String topic) {
        List<Lecture> lecturesByUserAndTopic = lectureService.lectureByUserAndTopic(userId,topic);
        return new ResponseEntity<>(lecturesByUserAndTopic, HttpStatus.OK);
    }

    @GetMapping("/{lectureId}/recommend-lectures")
    @Operation(summary = "유사도 기반 강의 추천 top 5")
    public ResponseEntity<List<Lecture>> recommendLectures(@PathVariable Integer lectureId) {
        Lecture lecture = lectureRepository.findByLectureId(lectureId);

        String title = lecture.getTitle();
        List<Lecture> lectures = lectureService.recommendLectures(title);
        return new ResponseEntity<>(lectures, HttpStatus.OK);
    }
}
