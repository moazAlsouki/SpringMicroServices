package com.graduation.order.config;

import brave.sampler.Sampler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class SetConfig {


    @Bean
    RestTemplate getrt(){
        return new RestTemplate();
    }
    @Bean
    public Sampler samplerOb() {

        return Sampler.ALWAYS_SAMPLE;
    }

}