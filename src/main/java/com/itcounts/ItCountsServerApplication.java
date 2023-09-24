package com.itcounts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ItCountsServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ItCountsServerApplication.class, args);
	}

}
