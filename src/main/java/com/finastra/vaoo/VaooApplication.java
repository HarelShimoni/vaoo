package com.finastra.vaoo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class VaooApplication {

    public static void main(String[] args) {
        SpringApplication.run(VaooApplication.class, args);
    }

}
