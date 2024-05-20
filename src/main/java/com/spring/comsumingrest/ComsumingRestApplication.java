package com.spring.comsumingrest;

import com.spring.comsumingrest.records.Quote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ComsumingRestApplication {

	private static final Logger log = LoggerFactory.getLogger(ComsumingRestApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ComsumingRestApplication.class, args);
	}

	//HttpRequest 를 보낼 수 있는 template 이예요! 아래의 !Test에 사용되는 Bean 이예요.
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	//Bean 이 스프링에 등록될 때 return 되는 Command 를 실행해요!
	@Bean
	@Profile("!test")
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			Quote quote = restTemplate.getForObject("http://localhost:8080/api/random", Quote.class);
			log.info(quote.toString());
		};
	}
}
