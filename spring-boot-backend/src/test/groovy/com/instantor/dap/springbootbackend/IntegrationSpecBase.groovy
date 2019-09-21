package com.instantor.dap.springbootbackend

import com.instantor.dap.springbootbackend.redis.AutoConfigureEmbeddedRedis
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.TestPropertySource
import spock.lang.Specification

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureEmbeddedRedis
@TestPropertySource(properties = [
        'spring.redis.host=localhost',
        'spring.redis.port=${embedded.redis.server.port}'
])
abstract class IntegrationSpecBase extends Specification {
}
