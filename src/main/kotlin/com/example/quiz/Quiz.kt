package com.example.quiz

import kotlinx.serialization.Serializable

@Serializable
data class QuizResponse(
    var id: Int = 0,
    val name: String,
    val questions: List<QuestionResponse>
)

@Serializable
data class QuestionResponse(
    val id: Int,
    val description: String,
    val answers: List<AnswerResponse>
)

@Serializable
data class AnswerResponse(
    val id: Int,
    val value: String,
    val correct: Boolean
)

class QuizModel(
    private var id: Int = 0,
    private val name: String,
    private val questions: List<QuestionModel>
) {
    fun toResponse(): QuizResponse {
        return QuizResponse(id, name, questions.map { it.toResponse() })
    }
}

class QuestionModel(
    val id: Int,
    val description: String,
    val answers: MutableCollection<AnswerModel> = mutableListOf()
) {
    fun toResponse(): QuestionResponse {
        return QuestionResponse(id, description, answers.map { it.toResponse() })
    }
}


class AnswerModel(
    private val id: Int,
    private val value: String,
    private val correct: Boolean = false
) {
    fun toResponse(): AnswerResponse {
        return AnswerResponse(id, value, correct)
    }
}

@Serializable
class CreateQuizRequest(
    val name: String,
    val questions: List<QuestionRequest>
)

@Serializable
data class QuestionRequest(
    val description: String,
    val answers: List<AnswerRequest>
)

@Serializable
data class AnswerRequest(
    val value: String,
    val correct: Boolean = false
)