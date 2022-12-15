package com.bateman.bignerdranch.Geoquiz

import android.util.Log
import androidx.lifecycle.ViewModel

//private const val TAG = "QuizViewModel"

class QuizViewModel : ViewModel() {

//    init {
//        Log.d(TAG, "ViewModel instance created")
//    }
//
//    override fun onCleared() {
//        super.onCleared()
//        Log.d(TAG, "ViewModel instance about to be destroyed")
//    }

    private val questionBank = listOf(
       Question(R.string.question_austrailia, true),
       Question(R.string.question_oceans, true),
       Question(R.string.question_mideast, false),
       Question(R.string.question_africa, false),
       Question(R.string.question_americas, true),
       Question(R.string.question_asia, true)
   )
    private var currentIndex = 0

    val currentQuestionAnswer: Boolean
        get() = questionBank[currentIndex].answer

    val currentQuestionText: Int
        get() = questionBank[currentIndex].textResId

    fun moveToNext() {
       // Log.d(TAG, "Updating question text", Exception())
        currentIndex = (currentIndex + 1) % questionBank.size
    }
}