package com.instantor.dap.springbootbackend.redis;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.util.SocketUtils;

/**
 * @see org.springframework.cloud.contract.wiremock.WireMockApplicationListener
 */
@Order(Ordered.LOWEST_PRECEDENCE)
public class EmbeddedRedisApplicationListener implements ApplicationListener<ApplicationPreparedEvent> {

    @Override
    public void onApplicationEvent(ApplicationPreparedEvent event) {
        registerPort(event.getApplicationContext().getEnvironment());
    }

    private void registerPort(ConfigurableEnvironment environment) {
        if (environment.getProperty("embedded.redis.server.port", Integer.class, 0) == 0) {
            MutablePropertySources propertySources = environment.getPropertySources();
            addPropertySource(propertySources);
            Map<String, Object> source = ((MapPropertySource) propertySources.get("embedded.redis")).getSource();
            source.put("embedded.redis.server.port", SocketUtils.findAvailableTcpPort(12500, 15000));
        }
    }

    private void addPropertySource(MutablePropertySources propertySources) {
        if (!propertySources.contains("embedded.redis")) {
            propertySources.addFirst(
                    new MapPropertySource("embedded.redis", new HashMap<>()));
        } else {
            // Move it up into first place
            PropertySource<?> propertySource = propertySources.remove("embedded.redis");
            propertySources.addFirst(propertySource);
        }
    }}
