package com.doomout.book.springboot.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    //소셜 로그인으로 반환되는 값 중 email 을 통해 가입/미가입 여부를 판단하는 메소드
    Optional<User> findByEmail(String email);
}
