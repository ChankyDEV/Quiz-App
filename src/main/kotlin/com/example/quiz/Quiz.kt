package com.example.quiz

import kotlinx.serialization.Serializable

@Serializable
class Quiz(var id: Int = -1, val questions: List<Question>)

@Serializable
data class Question(val question: String, val answers: List<Answer>)

@Serializable
data class Answer(val description: String, val correct: Boolean = false)

@Serializable
data class CreateQuizRequest(val questions: List<Question>)