package com.example.quiz

import com.example.common.Inject
import com.example.common.Routes
import io.ktor.server.application.*
import io.ktor.server.routing.*


fun Route.quizRouting() {
    val quizController: QuizController = Inject.get()
    route(Routes.quiz) {
        get {
            quizController.getAll(call)
        }
        get("{id}") {
            quizController.getOne(call)
        }
        post {
            quizController.addQuiz(call)
        }
    }
}
