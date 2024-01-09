package com.posco.education.service;

import com.posco.education.domain.entity.Lecture;
import com.posco.education.domain.entity.User;
import com.posco.education.repository.LectureRepository;
import com.posco.education.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final LectureRepository lectureRepository;

    @Autowired
    public UserService(UserRepository userRepository, LectureRepository lectureRepository) {
        this.userRepository = userRepository;
        this.lectureRepository = lectureRepository;
    }



}
