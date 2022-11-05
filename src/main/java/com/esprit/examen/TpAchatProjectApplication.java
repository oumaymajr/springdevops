package com.esprit.examen;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableScheduling
@SpringBootApplication
public class TpAchatProjectApplication {
	//@CrossOrigin("/http://localhost:4200") --> angular url

	public static void main(String[] args) {
		SpringApplication.run(TpAchatProjectApplication.class, args);
}
}