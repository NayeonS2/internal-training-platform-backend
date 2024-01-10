package com.posco.education.service;

import com.posco.education.domain.entity.Lecture;
import com.posco.education.domain.entity.Review;
import com.posco.education.domain.entity.User;
import com.posco.education.repository.LectureRepository;
import com.posco.education.repository.UserRepository;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

}
