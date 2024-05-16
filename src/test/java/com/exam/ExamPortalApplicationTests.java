package com.exam;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.exam.repo.UserRepository;

@SpringBootTest
class ExamPortalApplicationTests {
	
	@Autowired
	private UserRepository userRepository;
	

	@Test
	void contextLoads() {
	}
	
	@Test
	public void userRepoCreatedClass() {
		String name = this.userRepository.getClass().getName();
		System.out.println(name);
	}

}
