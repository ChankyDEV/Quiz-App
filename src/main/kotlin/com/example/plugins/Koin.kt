package com.example.plugins

import com.example.quiz.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import org.koin.dsl.module

val quizAppModule = module {
    single<QuizService> { QuizServiceImpl(get()) }
    single<QuizRepository> { QuizRepositoryImpl() }
    single { QuizController(get()) }
}

fun configureKoin() {
    startKoin {
        modules(quizAppModule)
    }
}

inline fun <reified T> inject(): T {
    return object : KoinComponent {
        val value: T by inject()
    }.value
}
