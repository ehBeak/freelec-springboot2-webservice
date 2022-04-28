package com.jojoldu.book.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * 메인 클래스
 */
//@EnableJpaAuditing// JPA Auditing 활성화
@SpringBootApplication // 스프링 부트의 자동 설정, 스프링 빈 읽기와 생성을 모두 자동으로 설정, 해당 패키지부터 적용
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);//run: 내장 WAS 실행
    }
}
