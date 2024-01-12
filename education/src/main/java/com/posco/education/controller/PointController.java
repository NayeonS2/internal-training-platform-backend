package com.posco.education.controller;


import com.posco.education.domain.dto.PointAddRequest;
import com.posco.education.domain.dto.PointAddResponse;
import com.posco.education.domain.dto.ReviewRequest;
import com.posco.education.domain.entity.Point;
import com.posco.education.domain.entity.Quiz;
import com.posco.education.service.PointService;
import com.posco.education.service.QuizService;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "유저 포인트 조회")
    public ResponseEntity<Point> pointByUser(@PathVariable String userId) {
        Point pointByUser = pointService.pointByUser(userId);
        return new ResponseEntity<>(pointByUser, HttpStatus.OK);
    }

    @PutMapping("/add-point")
    @Operation(summary = "포인트 증가 및 레벨 관리", description = "퀴즈 정답 주제에 맞는 포인트를 증가시키고, 포인트 구간에 따른 레벨을 관리")
    public ResponseEntity<PointAddResponse> addPoint(@RequestBody PointAddRequest pointAddRequest) {
        PointAddResponse pointAddResponse = pointService.addPoint(pointAddRequest.getUserId(),pointAddRequest.getTopic());
        return new ResponseEntity<>(pointAddResponse, HttpStatus.OK);
    }
}
