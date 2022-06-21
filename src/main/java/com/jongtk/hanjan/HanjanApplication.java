package com.jongtk.hanjan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
//모든 엔티티에 중복된 필드를 적용할때 유용 EX abstract BaseEntity
@EnableJpaAuditing
public class HanjanApplication {

	public static void main(String[] args) {
		SpringApplication.run(HanjanApplication.class, args);
	}

}
