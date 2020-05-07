package com.april.beer;

import com.april.beer.entity.Beer;
import com.april.beer.entity.Role;
import com.april.beer.entity.User;
import com.april.beer.repository.BeerRepository;
import com.april.beer.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@SpringBootApplication
public class BeerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BeerApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(BeerRepository beerRepository, UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        return args -> {
            beerRepository.save(new Beer("Lech", "Not the best beer"));
            beerRepository.save(new Beer("Tyskie", "Trully Not the best beer"));
            beerRepository.save(new Beer("Żywiec", "Better not to drink"));

            userRepository.save(new User("root", "root", "root", passwordEncoder.encode("123"), Arrays.asList(new Role("ROLE_USER"))));
        };
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
