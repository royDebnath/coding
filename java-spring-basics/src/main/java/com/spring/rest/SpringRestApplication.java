package com.spring.rest;

import com.spring.rest.model.Country;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SpringRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringRestApplication.class, args);
	}

	@Bean
	public List<Country> countries(){
		List<Country> countries = new ArrayList<>();
		countries.add(new Country(1, "India", "Asia"));
		countries.add(new Country(2, "USA", "America"));
		countries.add(new Country(3, "England", "Europe"));
		countries.add(new Country(4, "Ireland", "Europe"));
		return countries;
	}
}
