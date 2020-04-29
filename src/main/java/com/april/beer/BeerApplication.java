package com.april.beer;

import com.april.beer.entity.Beer;
import com.april.beer.repository.BeerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BeerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BeerApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(BeerRepository repository){
		return args -> {
			repository.save(new Beer("Lech", "Carlsberg"));
			repository.save(new Beer("Tyskie", "Kompania"));
			repository.save(new Beer("Żywiec", "Żywiec"));
		};
	}
}
