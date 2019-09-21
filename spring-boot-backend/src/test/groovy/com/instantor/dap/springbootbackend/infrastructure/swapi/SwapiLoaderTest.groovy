package com.instantor.dap.springbootbackend.infrastructure.swapi

import com.instantor.dap.springbootbackend.character.CharacterFacade
import com.instantor.dap.springbootbackend.character.dto.CharacterDto
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureMockRestServiceServer
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock
import org.springframework.context.annotation.Import
import org.springframework.test.context.TestPropertySource
import spock.lang.Specification

@RestClientTest(components = SwapiConfig)
@Import(WireMockResponseTemplateConfig)
@AutoConfigureWireMock(port = 0, stubs = 'classpath:mappings')
@AutoConfigureMockRestServiceServer(enabled = false)
@TestPropertySource(properties = [
        'debug=true',
        'swapi.base-url=http://localhost:${wiremock.server.port}/api'
])
class SwapiLoaderTest extends Specification {

    @SpringBean CharacterFacade characters = Mock()

    @Autowired SwapiLoader loader

    def "should load Star Wars API data"() {
        when:
            loader.run(null)
        then:
            20 * characters.add(_ as CharacterDto)
    }
}
