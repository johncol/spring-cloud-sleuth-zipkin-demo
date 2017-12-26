package com.example.customer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.sleuth.Sampler;
import org.springframework.cloud.sleuth.Span;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class SamplerConfig {

    @Bean
    public Sampler sampler() {
        return new Sampler() {
            @Override
            public boolean isSampled(Span span) {
                log.info("Custom Sleuth Sampler used");
                return true;
            }
        };
    }

}
