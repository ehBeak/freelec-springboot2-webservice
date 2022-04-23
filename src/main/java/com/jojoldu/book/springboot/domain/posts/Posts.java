package com.jojoldu.book.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Entity 클래스에서는 절대 Setter 메소드를 만들지 않는다 -> 목적과 의도를 나타낼 수 있는 메소드를 추가하자
 * 생성자를 통해 최종 값을 채운 후에 DB에 삽입하자
 * 값 변경이 필요한 경우 해당 이벤트에 맞는 public 메소드를 호출하자
 */
@Getter
@NoArgsConstructor // 기본 생성자 자동 추가
@Entity
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false) //String의 기본값: VARCHAR(255)
    private String content;

    private String author;

    @Builder // 빌더 패턴 클래스(어느필드에 어떤 값을 채워야 할지 명확하게 인지가능), 생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
