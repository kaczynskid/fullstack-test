package com.instantor.dap.springbootbackend.infrastructure.swapi

import com.github.tomakehurst.wiremock.core.WireMockConfiguration
import com.github.tomakehurst.wiremock.extension.responsetemplating.ResponseTemplateTransformer
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.cloud.contract.wiremock.WireMockConfigurationCustomizer
import org.springframework.context.annotation.Bean

@TestConfiguration
class WireMockResponseTemplateConfig {

    @Bean
    static WireMockConfigurationCustomizer wireMockConfigurationCustomizer() {
        return new WireMockConfigurationCustomizer() {
            @Override
            void customize(WireMockConfiguration config) {
                config.extensions(new ResponseTemplateTransformer(false))
            }
        }
    }
}
