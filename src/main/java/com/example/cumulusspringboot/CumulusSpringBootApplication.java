package com.example.cumulusspringboot;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@ComponentScan({"com.example.cumulusspringboot.controllers", "com.example.cumulusspringboot.config"})

@SpringBootApplication
public class CumulusSpringBootApplication {
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }


    public static void main(String[] args) {
        SpringApplication.run(CumulusSpringBootApplication.class, args);
    }

}
