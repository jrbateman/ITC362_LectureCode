package com.bateman.bignerdranch.Geoquiz

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.bateman.bignerdranch.Geoquiz.databinding.ActivityMainBinding

private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {

//    private lateinit var trueButton: Button
//    private lateinit var falseButton: Button
    //lateinit' allows initializing a not-null property outside of a constructor

    private val questionBank = listOf(
        Question(R.string.question_austrailia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true)
    )
    private var currentIndex = 0
    private lateinit var binding:ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//      setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d(TAG, "onCreate(Bundle?) called")
//        trueButton = findViewById(R.id.true_button)
//        falseButton = findViewById(R.id.false_button)


        //https://developer.android.com/develop/ui/views/notifications/snackbar/action

//        trueButton.setOnClickListener
        binding.trueButton.setOnClickListener{
//            val snackBar = Snackbar.make(
//                it,
//                "Correct",
//                Snackbar.LENGTH_LONG

            //snackBar.show()
            checkAnswer(true)

//        trueButton.setOnClickListener { view: View ->
//            Toast.makeText(
//                this,
//                R.string.correct_toast,
//                Toast.LENGTH_SHORT)
//                .show()
        }
//        falseButton.setOnClickListener
        binding.falseButton.setOnClickListener{
//            val snackBar = Snackbar.make(
//                it,
//                "Incorrect",
//                Snackbar.LENGTH_LONG
//            )
            //snackBar.setTextColor(Color.BLACK)
            //snackBar.setBackgroundTint(Color.RED)
            //snackBar.show()
            checkAnswer(false)
        }
//        falseButton.setOnClickListener { view: View ->
//            Toast.makeText(
//                this,
//                R.string.incorrect_toast,
//                Toast.LENGTH_SHORT)
//                .show()

        binding.nextButton.setOnClickListener {
            currentIndex = (currentIndex + 1) % questionBank.size
//            val questionTextResId = questionBank[currentIndex].textResId
//            binding.questionTextView.setText(questionTextResId)
            updateQuestion()
        }

//        val questionTextId = questionBank[currentIndex].textResId
//        binding.questionTextView.setText(questionTextId)

    }

    private fun updateQuestion(){
        val questionTextResId = questionBank[currentIndex].textResId
        binding.questionTextView.setText(questionTextResId)
    }

    private fun checkAnswer(userAnswer:Boolean) {
        val correctAnswer = questionBank[currentIndex].answer

        val messageResId = if (userAnswer == correctAnswer) {
            R.string.correct_toast
        } else {
            R.string.incorrect_toast
        }

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT)
            .show()
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() called")
    }
    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
    }

}
