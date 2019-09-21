package com.instantor.dap.springbootbackend

import io.restassured.module.mockmvc.RestAssuredMockMvc
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import spock.lang.Specification

abstract class ContractSpecBase extends Specification {

    @Autowired WebApplicationContext applicationContext

    void setup() {
        RestAssuredMockMvc.mockMvc(MockMvcBuilders.webAppContextSetup(applicationContext).build())
    }
}
