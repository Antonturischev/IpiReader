package ru.turishev.ipireader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootApplication
@EnableWebSecurity
@ComponentScan("ru.turishev.ipireader.*")
public class IpireaderApplication {
	public static void main(String[] args) {
		SpringApplication.run(IpireaderApplication.class, args);
	}

	@Bean
	public PasswordEncoder getPasswoedEncoder() {
		return new BCryptPasswordEncoder();
	}
}

