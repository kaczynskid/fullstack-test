package com.instantor.dap.springbootbackend.redis;

import java.io.IOException;

import org.redisson.spring.starter.RedissonAutoConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisOperations;

import redis.embedded.RedisServer;

@Configuration
@ConditionalOnClass(RedisOperations.class)
@AutoConfigureBefore({
        RedissonAutoConfiguration.class,
        RedisAutoConfiguration.class
})
public class EmbeddedRedisAutoConfiguration {

    @Bean(initMethod = "start", destroyMethod = "stop")
    public RedisServer embeddedRedisServer(@Value("${embedded.redis.server.port}") int port) throws IOException {
        return new RedisServer(port);
    }
}
