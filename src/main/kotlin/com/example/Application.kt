package com.example

import com.example.common.Inject
import com.example.common.quizAppModule
import com.example.plugins.configureRouting
import com.example.plugins.configureSerialization
import io.ktor.server.application.*


fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

@Suppress("unused")
fun Application.module() {
    Inject.create(listOf(quizAppModule))
    configureRouting()
    configureSerialization()
}

