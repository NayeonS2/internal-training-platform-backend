package com.posco.education.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.posco.education.domain.entity.Lecture;
import com.posco.education.domain.entity.Review;
import com.posco.education.domain.entity.User;
import com.posco.education.repository.LectureRepository;
import com.posco.education.repository.UserRepository;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LectureService {

    private final UserRepository userRepository;
    private final LectureRepository lectureRepository;

    @Autowired
    public LectureService(UserRepository userRepository, LectureRepository lectureRepository) {
        this.userRepository = userRepository;
        this.lectureRepository = lectureRepository;
    }

    @Transactional
    public void addLectureToMyLecture(String userId, Integer lectureId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        Lecture lecture = lectureRepository.findById(lectureId)
                .orElseThrow(() -> new RuntimeException("Lecture not found with ID: " + lectureId));

        user.getLectures().add(lecture);
        lecture.updateLecture(lecture.getLikeCnt() + 1);
        userRepository.save(user);
        lectureRepository.save(lecture);
    }

    @Transactional
    public void deleteLectureFromMyLecture(String userId, Integer lectureId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        Lecture lecture = lectureRepository.findById(lectureId)
                .orElseThrow(() -> new RuntimeException("Lecture not found with ID: " + lectureId));

        user.getLectures().remove(lecture);
        lecture.updateLecture(lecture.getLikeCnt() - 1);
        userRepository.save(user);
        lectureRepository.save(lecture);
    }

    public List<Lecture> lectureByUser(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        List<Lecture> userLectures = user.getLectures();

        return userLectures;
    }

    public List<Lecture> lectureByTopic(String topic) {
        List<Lecture> lecturesByTopic = lectureRepository.findByTopic(topic);

        return lecturesByTopic;
    }

    public List<Lecture> lectureByTopicOrderByLike(String topic) {
        List<Lecture> lecturesByTopicOrderByLike = lectureRepository.findByTopicOrderByLikeCntDesc(topic);

        return lecturesByTopicOrderByLike;
    }

    public List<Lecture> lectureByUserAndTopic(String userId, String topic) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        List<Lecture> userLectures = user.getLectures();

        List<Lecture> lecturesByUserAndTopic = new ArrayList<>();

        for (Lecture lec : userLectures) {
            if (lec.getTopic().equals(topic)) {
                lecturesByUserAndTopic.add(lec);
            }
        }

        return lecturesByUserAndTopic;
    }


    public List<Lecture> recommendLectures(String title) {

        List<Lecture> result = new ArrayList<>();

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("title", title);

        // Call Flask API
        String flaskApiUrl = "http://192.168.0.104:5000/recommend";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Prepare the request body
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);

        // Make the request
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(new SimpleClientHttpRequestFactory());
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(flaskApiUrl, request, String.class);
        System.out.println(responseEntity.getBody());
        // ResponseEntity의 body를 JSON 문자열로 얻기
        String jsonString = responseEntity.getBody();

//        System.out.println(jsonString);

        // JSON 문자열을 LinkedHashMap으로 변환
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Map<String, Object> resultMap = objectMapper.readValue(jsonString, Map.class);

            System.out.println(resultMap);
            List<Map<String, Object>> recommendations = (List<Map<String, Object>>) resultMap.get("recommendations");

            for (Map<String, Object> rec : recommendations) {
                Integer lectureId = (Integer) rec.get("lecture_id");
                Lecture lecture = lectureRepository.findByLectureId(lectureId);

                if (lecture.getThumbnail_url().contains("maxresdefault")) {
                    result.add(lecture);
                }
            }

            return result;

        } catch (Exception e) {
            e.printStackTrace();
            return result;
        }
    }

}
