package com.storemgnmt.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableJpaRepositories("com.storemgnmt.repository")
@EntityScan("com.storemgnmt.entities")
@ComponentScan(basePackages ="com.storemgnmt")
public class StoreStarter {

    public static void main(String[] args) {
        SpringApplication.run(StoreStarter.class, args);
    }
}
