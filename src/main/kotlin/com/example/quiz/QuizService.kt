package com.example.quiz

import io.ktor.server.plugins.*

interface QuizService {
    fun getAll(): List<QuizModel>
    fun getOne(id: Int): QuizModel
    fun add(quiz: CreateQuizRequest)
}


class QuizServiceImpl(private val repository: QuizRepository) : QuizService {
    override fun getAll(): List<QuizModel> {
        return repository.getAll()
    }

    override fun getOne(id: Int): QuizModel {
        return repository.getOne(id) ?: throw NotFoundException()
    }

    override fun add(quiz: CreateQuizRequest) {
        repository.add(quiz)
    }
}