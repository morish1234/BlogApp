package com.blogApp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class BlogAppApplicationTests {
	public static void main(String[] args) {
		PasswordEncoder ps=new BCryptPasswordEncoder();
		System.out.println(ps.encode("testing"));
	}


}
