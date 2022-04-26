package com.example.common

import com.example.quiz.*
import org.koin.core.Koin
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module

class Inject {
    companion object {
        lateinit var koin: Koin
        fun create(modules: List<Module>) {
            val app = KoinApplication.init()
            startKoin(app)
            app.modules(modules)
            koin = app.koin
        }

        inline fun <reified T> get(): T = koin.get()
    }
}


val quizAppModule = module {
    single<QuizService> { QuizServiceImpl(get()) }
    single<QuizRepository> { QuizRepositoryImpl() }
    single { QuizController(get()) }
}