package com.example.quiz

import org.ktorm.entity.Entity
import org.ktorm.schema.Table
import org.ktorm.schema.boolean
import org.ktorm.schema.int
import org.ktorm.schema.varchar


interface Answer : Entity<Answer> {
    companion object : Entity.Factory<Answer>()

    val id: Int
    val value: String
    val correct: Boolean
    val question: Question
}

interface Question : Entity<Question> {
    companion object : Entity.Factory<Question>()

    val id: Int
    val description: String
    val quiz: Quiz
}

interface Quiz : Entity<Quiz> {
    companion object : Entity.Factory<Quiz>()

    val id: Int
    val name: String
}

object Quizes : Table<Quiz>("quizes") {
    val id = int("id").primaryKey().bindTo { it.id }
    val name = varchar("name").bindTo { it.name }
}

object Questions : Table<Question>("questions") {
    val id = int("id").primaryKey().bindTo { it.id }
    val description = varchar("description").bindTo { it.description }
    val quizId = int("quiz_id").references(Quizes) { it.quiz }
}

object Answers : Table<Answer>("answers") {
    val id = int("id").primaryKey().bindTo { it.id }
    val value = varchar("value").bindTo { it.value }
    val correct = boolean("correct").bindTo { it.correct }
    val questionId = int("question_id").references(Questions) { it.question }
}

