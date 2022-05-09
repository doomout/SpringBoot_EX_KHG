package com.doomout.book.springboot.web;

import com.doomout.book.springboot.config.auth.SecurityConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)  //테스트할 때 JUnit에 내장된 실행자 외에 다른 실행자를 실행
//@WebMvcTest(controllers = HelloController.class)

// 스캔 대상에서 SecurityConfig 제외
@WebMvcTest(controllers = HelloController.class,
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)
        }
)
public class HelloControllerTest {
    @Autowired //스프링이 관리하는 Bean을 주입받는다.
    private MockMvc mvc; //웹 API를 테스트 할 때 사용, 스프링 MVC테스트의 시작점

    @Test
    @WithMockUser(roles="USER")
    public void hello가_리턴된다() throws Exception{
        String hello = "hello";

        mvc.perform(get("/hello")) // /hello 주소로 HTTP GET요청
                .andExpect(status().isOk()) //결과 검증 OK(200) 인지 검증
                .andExpect(content().string(hello)); //결과를 검증  hello를 리턴하기에 맞는지 검증
    }

    @Test
    @WithMockUser(roles="USER")
    public void helloDto가_리턴된다() throws Exception {
        String name = "hello";
        int amount =  1000;
        mvc.perform(
                get("/hello/dto")
                        .param("name", name)
                        .param("amount", String.valueOf(amount))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name",is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));
    }
}
