package com.jojoldu.book.springboot.web;

import com.jojoldu.book.springboot.config.auth.SecurityConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;

// 테스트 코드 작성
@ExtendWith(SpringExtension.class) // jUnit에 내장된 실행자 외에 다른 실행자(SpringExtension) 실행시킴 (연결자 역할)
@WebMvcTest(controllers = HelloController.class,
    excludeFilters = {
            @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
            classes = SecurityConfig.class)
    }
)// Web관련 스프링 테스트 어노테이션-> @Controller관련 어노테이션 등만 사용가능
class HelloControllerTest {

    @Autowired//빈 주입
    private MockMvc mvc; // 웹 API를 테스트할 때 사용 (HTTP 메서드, mvc 테스트의 시작점)

    @WithMockUser(roles = "USER")
    @Test
    void return_hello() throws Exception {
        String hello = "hello";
        mvc.perform(get("/hello"))//get요청을 한다는 것, 체이닝이 지원됨 (여러 검증 기능 선언 가능)
                .andExpect(status().isOk())// mvc.perform의 결과를 검증 - status(200)
                .andExpect(content().string(hello));// mvc.perform의 결과를 검증 - 응답내용("hello")
    }

    @WithMockUser(roles = "USER")
    @Test
    void return_helloDto() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                        get("/hello/dto")
                                .param("name", name) // 파라미터 넘기기
                                .param("amount", String.valueOf(amount)) //String만 허용
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name))) // json응답 값을 필드별로 검증하도록 함
                .andExpect(jsonPath("$.amount", is(amount)));

    }
}