package org.example.application;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "org.example.infrastructure.repository")
@EntityScan(basePackages = "org.example.infrastructure.repository.entity")
@ComponentScan(basePackages = "org.example", excludeFilters = @ComponentScan.Filter(RestController.class))
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}