package com.example.quiz

interface QuizRepository {
    fun getAll(): List<Quiz>
    fun getOne(id: Int): Quiz?
    fun add(quiz: CreateQuizRequest)
}

class QuizRepositoryImpl : QuizRepository {
    override fun getAll(): List<Quiz> {
        return quizes;
    }

    override fun getOne(id: Int): Quiz? {
        return quizes.find { quiz -> quiz.id == id }
    }

    override fun add(quizRequest: CreateQuizRequest) {
        val quiz = Quiz(quizes.size, quizRequest.questions)
        quizes.add(quiz)
    }
}


val yesAnswer: Answer = Answer("Yes", true)
val noAnswer: Answer = Answer("No")
val question1: Question = Question("Are you high?", listOf(yesAnswer, noAnswer))

val quizes = mutableListOf<Quiz>(
    Quiz(0, listOf(question1)),
)