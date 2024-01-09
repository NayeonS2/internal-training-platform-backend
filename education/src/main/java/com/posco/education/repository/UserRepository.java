package com.posco.education.repository;

import com.posco.education.domain.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

//    Optional<User> findByUserId(String userId);
//    Page<User> findAllByNicknameContains(String nickname, PageRequest pageRequest);
//    Boolean existsByLoginId(String loginId);

}
