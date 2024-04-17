package com.abc.blog.model;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
// @DynamicInsert // insert시에 null인 필드를 제외시켜준다.
public class User {

	@Id // Primary Key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
	private int id; // 시퀀스, auto_increment

	@Column(nullable = false, length = 30, unique = true)
	private String username; // 아이디

	@Column(nullable = false, length = 100) // 123456 = > 해쉬 (비밀번호 암호화)
	private String password;

	@Column(nullable = false, length = 50)
	private String email; // myEmail, my_email

	// @ColumnDefault("user")
	// DB는 RoleType이라는 게 없다.
	@Enumerated(EnumType.STRING)
	private RoleType role; // Enum을 쓰는게 좋다. // ADMIN, USER
	
	private String oauth; // kakao

	@CreationTimestamp // 내가 직접 시간을 넣으려면 Timestamp.valueOf(LocalDateTime.now())
	private Timestamp createDate;

}