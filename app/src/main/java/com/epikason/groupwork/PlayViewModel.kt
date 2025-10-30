package com.epikason.groupwork

import androidx.lifecycle.ViewModel
import com.example.quiz_app.LocalDataSource
import com.example.quiz_app.Quiz

class PlayViewModel : ViewModel() {
    private var quizList = LocalDataSource.questions
    private var currentIndex = 0
    private var score = 0

    // বর্তমান প্রশ্ন রিটার্ন করবে
    fun getCurrentQuestion(): Quiz {
        return quizList[currentIndex]
    }

    // পরের প্রশ্ন রিটার্ন করবে
    fun getNextQuestion(): Quiz? {
        if (currentIndex < quizList.size - 1) {
            currentIndex++
            return quizList[currentIndex]
        } else {
            return null
        }
    }

    // সঠিক হলে ১ বাড়বে, ভুল হলে কিছুই হবে না
    fun checkAnswer(selectedIndex: Int) {
        val currentQuiz = quizList[currentIndex]

        if (selectedIndex == currentQuiz.correctAnswerIndex) {
            score++ // ✅ শুধু সঠিক হলে বাড়বে
        }
        // ❌ ভুল হলে কোনো পরিবর্তন হবে না
    }

    // মোট স্কোর রিটার্ন করবে
    fun getScore(): Int {
        return score
    }

    // মোট প্রশ্নের সংখ্যা
    fun getTotalQuestions(): Int {
        return quizList.size
    }
}