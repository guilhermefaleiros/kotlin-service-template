package com.guilhermefaleiros.kotlin.service.template.infrastructure.config.database

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "jdbc")
open class JdbcProperties {
    var username: String = ""
    var password: String = ""
    var initSql: String = ""
    var url: String = ""
    var schema: String = ""
    var maxPoolSize: Int = 0
    var maxLifetimeInMinutes: Long = 0
    var leakDetectionThresholdInMilliseconds: Long = 0
    var connectionTimeoutInMilliseconds: Long = 0
}
