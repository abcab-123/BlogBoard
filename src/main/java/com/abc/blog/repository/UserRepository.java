package com.abc.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abc.blog.model.User;

// DAO
// 자동으로 Bean으로 등록, @Repository 생략 가능.
public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByUsername(String name);
}


//JPA Naming 쿼리
	// username과 password를 받아 일치하는 사용자 조회
	/* User findByUsernameAndPassword(String username, String password); */