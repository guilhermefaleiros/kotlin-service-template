package com.guilhermefaleiros.kotlin.service.template.infrastructure.config.database

import com.zaxxer.hikari.HikariDataSource
import org.postgresql.Driver
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import javax.sql.DataSource

@Configuration
open class JdbcConfiguration(
    private val jdbcProperties: JdbcProperties,
) {
    @Bean
    open fun dataSource(): DataSource {
        return createDataSource(jdbcProperties)
    }

    @Bean
    open fun jdbcTemplate(dataSource: DataSource): NamedParameterJdbcTemplate {
        return NamedParameterJdbcTemplate(dataSource)
    }

    private fun createDataSource(dataSourceProperties: JdbcProperties): HikariDataSource {
        val dataSource = HikariDataSource()
        dataSource.driverClassName = Driver::class.java.getName()
        dataSource.jdbcUrl = dataSourceProperties.url
        dataSource.schema = dataSourceProperties.schema
        dataSource.username = jdbcProperties.username
        dataSource.password = jdbcProperties.password
        dataSource.maximumPoolSize = dataSourceProperties.maxPoolSize
        dataSource.maxLifetime = dataSourceProperties.maxLifetimeInMinutes
        dataSource.leakDetectionThreshold = dataSourceProperties.leakDetectionThresholdInMilliseconds
        dataSource.connectionTimeout = dataSourceProperties.connectionTimeoutInMilliseconds
        dataSource.connectionInitSql = jdbcProperties.initSql
        return dataSource
    }
}
