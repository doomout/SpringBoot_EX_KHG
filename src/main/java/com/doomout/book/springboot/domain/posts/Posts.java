package com.doomout.book.springboot.domain.posts;
import com.doomout.book.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter //롬복의 어노테이션, 클래스내 Getter 메소드 자동 생성
@NoArgsConstructor //롬복의 어노테이션, 기본 생성자 자동 생성
@Entity //JPA의 어노테이션,테이블과 링크될 클래스 임을 나타냄
public class Posts extends BaseTimeEntity {
    @Id //해당 테이블의 PK 필드
    @GeneratedValue(strategy = GenerationType.IDENTITY) //PK의 생성 규칙을 표시
    private Long id; //PK가 Long  타입

    @Column(length = 500, nullable = false) //문자 형식 varchar(500), not null
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false) //문자 형식 text, not null
    private String content;

    private String author;

    @Builder //해당 클래스의 빌더 클래스 생성
    public Posts(String title, String content, String author) {
        this.title = title; //문자 형식 varchar(500), not null
        this.content = content; //문자 형식 text, not null
        this.author = author; //문자 형식 text, not null
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
