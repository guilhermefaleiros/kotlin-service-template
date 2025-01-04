val jacocoVersion: String by project

plugins {
    kotlin("jvm") version "2.0.21"
    id("org.springframework.boot") version "3.3.6" apply false
    id("io.spring.dependency-management") version "1.1.3" apply false
    id("jacoco")
    id("jacoco-report-aggregation")
    id("org.jlleitschuh.gradle.ktlint") version "12.1.2"
}

group = "com.guilhermefaleiros"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

subprojects {
    apply(plugin = "kotlin")
    apply(plugin = "java")
    apply(plugin = "jacoco")
    apply(plugin = "org.jlleitschuh.gradle.ktlint")

    java {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

    kotlin {
        jvmToolchain(21)
    }

    repositories {
        mavenCentral()
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    jacoco {
        toolVersion = jacocoVersion
    }

    tasks.jacocoTestReport {
        dependsOn(tasks.test)
        reports {
            xml.required.set(true)
            html.required.set(true)
            csv.required.set(false)
        }
    }
}

tasks.register<JacocoReport>("jacocoAggregateReport") {
    dependsOn(subprojects.map { it.tasks.named("test") })

    additionalSourceDirs.from(
        files(
            subprojects.flatMap {
                it.extensions.getByType<SourceSetContainer>().named("main").get().allSource.srcDirs
            },
        ),
    )
    sourceDirectories.from(
        files(subprojects.flatMap { it.extensions.getByType<SourceSetContainer>().named("main").get().allSource.srcDirs }),
    )
    classDirectories.from(files(subprojects.flatMap { it.extensions.getByType<SourceSetContainer>().named("main").get().output }))

    executionData.from(files(subprojects.flatMap { it.tasks.named<JacocoReport>("jacocoTestReport").get().executionData }))

    reports {
        xml.required.set(true)
        html.required.set(true)
        csv.required.set(false)
    }
}

tasks.check {
    dependsOn("jacocoAggregateReport")
}
