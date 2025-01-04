package com.guilhermefaleiros.kotlin.service.template.infrastructure

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@ComponentScan(basePackages = ["com.guilhermefaleiros.kotlin.service.template"])
@ConfigurationPropertiesScan(basePackages = ["com.guilhermefaleiros.kotlin.service.template"])
@SpringBootApplication
open class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
