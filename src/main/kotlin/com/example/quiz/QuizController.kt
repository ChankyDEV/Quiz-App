package com.example.quiz

import com.example.common.Messages
import com.example.common.created
import com.example.common.ok
import com.example.common.request
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.request.*

class QuizController(private val quizService: QuizService) {
    suspend fun getAll(call: ApplicationCall) {
        request(call) {
            val quizes = quizService.getAll()
            it.ok(quizes.map { quiz -> quiz.toResponse() })
        }
    }

    suspend fun getOne(call: ApplicationCall) {
        request(call) {
            val id = it.parameters["id"]?.toInt() ?: throw BadRequestException(Messages.BadRequest)
            val quiz = quizService.getOne(id)
            it.ok(quiz.toResponse())
        }
    }

    suspend fun addQuiz(call: ApplicationCall) {
        request(call) {
            val quiz: CreateQuizRequest = it.receiveOrNull() ?: throw BadRequestException(Messages.BadRequest)
            quizService.add(quiz)
            it.created()
        }
    }
}