package com.abc.blog.model;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
	private int id;

	@Column(nullable = false, length = 100)
	private String title;

	@Lob // 데이터베이스의 LOB 타입으로 매핑, 대용량 바이너리/문자 데이터 저장
	@Column(columnDefinition = "LONGTEXT")
	private String content; // content는 섬머노트 에디터 입력 데이터로, HTML 태그 포함 문자열

	private int count; // 조회수

	@ManyToOne // 일대다(1:N) 관계 지정, 다쪽은 여러 개, 하나쪽은 한 개 참조
	@JoinColumn(name = "userId")
	private User user; // 이 User 변수는 데이터베이스와 연결된 사용자 정보를 가리키지만, 자바에서는 실제 사용자 객체 자신을 사용할 수 있다.
	
	@OneToMany(mappedBy = "board",fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)  //mappedBy 연관관계의 주인이 아니다 (난 FK가 아니에요) DB에 컬럼을 만들기 X / 테이블생성되는 FK가 아니고 그냥 DB만 끄
    //@JoinColumn(name = "replyId")가 필요없는 이유? ->
	@JsonIgnoreProperties({"board"})
    private List<Reply> replys;  //댓글은 여러개가 달릴수있기때문에 List로 생성

	@CreationTimestamp
	private Timestamp createDate;
}