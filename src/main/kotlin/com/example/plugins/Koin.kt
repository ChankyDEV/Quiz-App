package com.example.plugins

import com.example.quiz.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module
import org.ktorm.database.Database


fun prepareModule(db: Database): Module {
    return module {
        single<QuizService> { QuizServiceImpl(get()) }
        single<QuizRepository> { QuizRepositoryImpl(get()) }
        single { QuizController(get()) }
        single { db }
    };
}

fun configureKoin(db: Database) {
    val quizModule = prepareModule(db)
    startKoin {
        modules(quizModule)
    }
}

inline fun <reified T> inject(): T {
    return object : KoinComponent {
        val value: T by inject()
    }.value
}
