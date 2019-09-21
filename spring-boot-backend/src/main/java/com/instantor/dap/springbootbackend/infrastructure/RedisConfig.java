package com.instantor.dap.springbootbackend.infrastructure;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

import com.instantor.dap.springbootbackend.StarWarsWikiApp;

@Configuration
@EnableRedisRepositories(basePackageClasses = StarWarsWikiApp.class)
public class RedisConfig {
}
