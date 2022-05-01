package com.example.plugins

import com.example.quiz.Answers
import com.example.quiz.Questions
import com.example.quiz.Quizes
import org.ktorm.database.Database


fun configureDatabase(): Database {
    return Database.connect(
        "jdbc:postgresql://localhost:5432/quiz", driver = "org.postgresql.Driver",
        user = "postgres", password = "Mzqpmpqz123."
    )
}