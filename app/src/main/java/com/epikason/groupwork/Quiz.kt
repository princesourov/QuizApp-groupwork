package com.example.quiz_app

data class Quiz(
    val question: String,
    val options: List<String>,
    val correctAnswerIndex: Int
)
