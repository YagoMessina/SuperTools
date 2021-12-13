package com.sambuini.launcher;

import com.sambuini.auth.AuthApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SuperToolsApplication {

	public static void main(String[] args) {
		Class<?>[] applications = {SuperToolsApplication.class, AuthApplication.class};
		SpringApplication.run(applications, args);
	}

}
