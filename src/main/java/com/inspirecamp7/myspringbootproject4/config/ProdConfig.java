package com.inspirecamp7.myspringbootproject4.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("prod")
@Configuration
public class ProdConfig {

    @Bean
    public CustomVO customVOa() {
        return CustomVO.builder() //CustomerV0Builder
                .mode("운영 Mode")
                .rate(1.9)
                .build();
    }
}
