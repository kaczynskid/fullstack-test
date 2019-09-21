package com.instantor.dap.springbootbackend.infrastructure.swapi;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;

import com.instantor.dap.springbootbackend.character.CharacterFacade;

@Configuration
@Profile("!test")
@EnableConfigurationProperties(SwapiProperties.class)
class SwapiConfig {

    @Bean RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean SwapiClient swapiClient(SwapiProperties properties, RestTemplate json) {
        return new SwapiClient(properties, json);
    }

    @Bean SwapiLoader swapiLoader(SwapiClient swapi, CharacterFacade characters) {
        return new SwapiLoader(swapi, characters);
    }
}

