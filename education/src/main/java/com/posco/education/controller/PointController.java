package com.posco.education.controller;


import com.posco.education.domain.dto.PointAddRequest;
import com.posco.education.domain.dto.PointAddResponse;
import com.posco.education.domain.dto.ReviewRequest;
import com.posco.education.domain.entity.Point;
import com.posco.education.domain.entity.Quiz;
import com.posco.education.service.PointService;
import com.posco.education.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/point")
public class PointController {

    private final PointService pointService;

    @Autowired
    public PointController(PointService pointService) {
        this.pointService = pointService;

    }

    @GetMapping("/{userId}/user-point")
    public ResponseEntity<Point> pointByUser(@PathVariable String userId) {
        Point pointByUser = pointService.pointByUser(userId);
        return new ResponseEntity<>(pointByUser, HttpStatus.OK);
    }

    @PutMapping("/add-point")
    public ResponseEntity<PointAddResponse> addPoint(@RequestBody PointAddRequest pointAddRequest) {
        PointAddResponse pointAddResponse = pointService.addPoint(pointAddRequest.getUserId(),pointAddRequest.getTopic());
        return new ResponseEntity<>(pointAddResponse, HttpStatus.OK);
    }
}
