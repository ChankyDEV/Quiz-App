import java.util.regex.Pattern.compile

val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project
val koin_version: String by project
val ktorm_version: String by project

plugins {
    kotlin("jvm") version "1.6.21"
    application
    id("org.jetbrains.kotlin.plugin.serialization") version "1.6.21"
}

group = "com.example"
version = "0.0.1"
application {
    mainClass.set("io.ktor.server.netty.EngineMain")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

dependencies {
    compile("io.insert-koin:koin-core:$koin_version")
    compile("io.insert-koin:koin-ktor:$koin_version")
    compile("org.ktorm:ktorm-core:${ktorm_version}")
    implementation("org.ktorm:ktorm-core:${ktorm_version}")
    implementation("org.postgresql:postgresql:42.3.4")
    implementation("io.insert-koin:koin-core:$koin_version")
    testImplementation("io.insert-koin:koin-test:$koin_version")
    implementation("io.insert-koin:koin-ktor:$koin_version")
    implementation("io.insert-koin:koin-logger-slf4j:$koin_version")
    implementation("io.ktor:ktor-server-core-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-content-negotiation-jvm:$ktor_version")
    implementation("io.ktor:ktor-serialization-kotlinx-json-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-netty-jvm:$ktor_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    testImplementation("io.ktor:ktor-server-tests-jvm:$ktor_version")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
}