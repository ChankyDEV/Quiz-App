package com.example.quiz

import io.ktor.server.plugins.*

interface QuizService {
    fun getAll(): List<Quiz>
    fun getOne(id: Int): Quiz
    fun add(quiz: CreateQuizRequest)
}


class QuizServiceImpl(private val repository: QuizRepository) : QuizService {
    override fun getAll(): List<Quiz> {
        return repository.getAll()
    }

    override fun getOne(id: Int): Quiz {
        return repository.getOne(id) ?: throw NotFoundException()
    }

    override fun add(quiz: CreateQuizRequest) {
        repository.add(quiz)
    }
}