package com.guilhermefaleiros.kotlin.service.template.infrastructure

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles("test")
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
open class IntegrationTest {
    @Autowired
    private lateinit var template: NamedParameterJdbcTemplate

    protected fun clearDatabase() {
        template.update("DELETE FROM users", emptyMap<String, Any>())
    }
}
