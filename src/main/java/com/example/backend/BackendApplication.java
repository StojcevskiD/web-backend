package com.example.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class BackendApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(BackendApplication.class, args);
    }

//    @Bean
//    PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder(10);
//    }

//    @Bean
//    public MultipartConfigElement multipartConfigElement() {
//        MultipartConfigFactory factory = new MultipartConfigFactory();
//        //Largest file
//        factory.setMaxFileSize(DataSize.parse("10MB")); //KB,MB
//        ///  Set the total size of the total uploaded data
//        factory.setMaxRequestSize(DataSize.parse("15MB"));
//        return factory.createMultipartConfig();
//    }

}
