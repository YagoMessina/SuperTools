package com.sambuini.launcher;

import com.sambuini.auth.AuthApplication;
import com.sambuini.error.ErrorApplication;
import com.sambuini.file.FileApplication;
import com.sambuini.note.NoteApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SuperToolsApplication {

	public static void main(String[] args) {
		Class<?>[] applications = {
				SuperToolsApplication.class,
				AuthApplication.class,
				ErrorApplication.class,
				NoteApplication.class,
				FileApplication.class
				};
		SpringApplication.run(applications, args);
	}

}
