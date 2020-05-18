package org.zerock.simpleboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SimpleboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleboardApplication.class, args);
	}

}
