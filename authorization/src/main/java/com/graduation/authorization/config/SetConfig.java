package com.graduation.authorization.config;

import brave.sampler.Sampler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SetConfig {

    @Bean
    public Sampler samplerOb() {

        return Sampler.ALWAYS_SAMPLE;
    }

}