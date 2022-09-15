package com.jongtk.hanjan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableJpaAuditing //모든 엔티티에 중복된 필드를 적용할때 유용 EX) abstract BaseEntity
@EnableAsync // 기본 제공되는 스레드풀을 사용하여 비동기 사용 가능하도록
public class HanjanApplication {

	public static void main(String[] args) {
		SpringApplication.run(HanjanApplication.class, args);
	}

}
