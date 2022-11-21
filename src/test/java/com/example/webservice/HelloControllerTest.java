package com.example.webservice;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

// 스프링 테스트 어노테이션 중에서 web과 관련된 테스트를 실행할 수 있다.
@WebMvcTest(controllers = HelloController.class)
public class HelloControllerTest {

	/*
	 	MocMvc
	 	- 웹 API를 테스트할 때 사용
	 	- 스프링 MVC 테스트
	 	- 이 클래스를 통해 HTTP GET, POSTE 메소드 등에 대한 테스트를 할 수 있다.
	 	
	 */
	
	@Autowired
	private MockMvc mvc;
	
	@Test
	public void hello가_리턴된다() throws Exception {
		String hello = "hello";
		
		mvc.perform(get("/hello"))    // MockMvc를 통해 /hello 주소로 GET 메소드 요청을 한다.
		.andExpect(status().isOk())  // Http header의 status를 검증한다.
		.andExpect(content().string(hello));  // mvc.perform()의 결과를 검증한다.	
	}
	
	@Test
	public void helloDto가_리턴된다() throws Exception {
		String name = "hello";
		int age = 20;
		
		/*
		    jsonPath
		    - JSON 응답값을 필드별로 검증
		    - $.를 기준으로 필드명을 표기
		 */
		
		mvc.perform(
				get("/hello/dto")
					.param("name", name)
					.param("age", String.valueOf(age)))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.name", is(name)))
		.andExpect(jsonPath("$.age", is(age)));
	}
}
