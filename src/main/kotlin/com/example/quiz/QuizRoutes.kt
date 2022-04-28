package com.example.quiz

import com.example.common.Routes
import com.example.plugins.inject
import io.ktor.server.application.*
import io.ktor.server.routing.*


fun Route.quizRouting() {
    val quizController: QuizController = inject()
    route(Routes.Quiz) {
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
