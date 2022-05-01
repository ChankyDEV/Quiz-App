package com.example.quiz

import org.ktorm.database.Database
import org.ktorm.dsl.*
import org.ktorm.entity.find
import org.ktorm.entity.map
import org.ktorm.entity.sequenceOf

interface QuizRepository {
    fun getAll(): List<QuizModel>
    fun getOne(id: Int): QuizModel?
    fun add(createQuizRequest: CreateQuizRequest)
}

class QuizRepositoryImpl(private val db: Database) : QuizRepository {
    override fun getAll(): List<QuizModel> {
        return db.sequenceOf(Quizes).map {
            getOne(it.id)!!
        }
    }

    override fun getOne(id: Int): QuizModel? {
        var quizModel: QuizModel? = null
        val quiz = db.sequenceOf(Quizes).find { it.id eq id }
        if (quiz != null) {
            val questions = db.from(Questions)
                .select()
                .where(Questions.quizId eq quiz.id)
                .map {
                    QuestionModel(
                        it[Questions.id]!!,
                        it[Questions.description]!!
                    )
                }
            for (question in questions) {
                val answers = db.from(Answers)
                    .select()
                    .where(Answers.questionId eq question.id)
                    .map {
                        AnswerModel(
                            it[Answers.id]!!,
                            it[Answers.value]!!,
                            it[Answers.correct]!!
                        )
                    }
                question.answers.addAll(answers)
            }
            quizModel = QuizModel(quiz.id, quiz.name, questions)
        }
        return quizModel
    }

    override fun add(createQuizRequest: CreateQuizRequest) {
        db.useTransaction {
            val quizId = db.insertAndGenerateKey(Quizes) {
                set(it.name, createQuizRequest.name)
            }
            createQuizRequest.questions.forEach { questionModel ->
                val questionId = db.insertAndGenerateKey(Questions) {
                    set(it.quizId, quizId)
                    set(it.description, questionModel.description)
                }
                questionModel.answers.forEach { answerModel ->
                    db.insertAndGenerateKey(Answers) {
                        set(it.value, answerModel.value)
                        set(it.correct, answerModel.correct)
                        set(it.questionId, questionId)
                    }
                }
            }
        }
    }
}
