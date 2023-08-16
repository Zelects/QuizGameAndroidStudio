package com.example.project
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class QuizActivity : AppCompatActivity() {
    private var currentQuestionIndex = 0
    private var score = 0

    private val questions = listOf(
        Question("What class is this", listOf("MAC 220", "MAC 280", "MAC 250", "MAC 110"), 0),
        Question("How many sides are in a octagon", listOf("4", "8", "16", "12"), 1),
        Question("What is the largest mammal?", listOf("Elephant", "Whale", "Giraffe", "Horse"), 1),
        Question("where is the eiffel tower located at?", listOf("India", "Paris", "Egypt", "Italy"), 1),
        Question("What is the chemical symbol for water?", listOf("O", "W", "H2O", "WA"), 2),
        Question("What is the currency of Japan?", listOf("Yuan", "Euro", "Yen", "Dollar"), 2),
        Question("Who painted the Mona Lisa?", listOf("Michelangelo", "Vincent van Gogh", "Leonardo da Vinci", "Pablo Picasso"), 2),
        Question("Which planet is known as the 'Red Planet'?", listOf("Venus", "Mars", "Mercury", "Jupiter"), 1),
        Question("Which animal is the 'king of the jungle'?", listOf("Lion", "Tiger", "Elephant", "Leopard"), 0),
        Question("how many credits do you need to graduate?", listOf("60", "30", "45", "55"), 0)
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        showQuestion()
    }

    private fun showQuestion() {
        val questionTextView = findViewById<TextView>(R.id.questionTextView)
        val option1Button = findViewById<Button>(R.id.option1Button)
        val option2Button = findViewById<Button>(R.id.option2Button)
        val option3Button = findViewById<Button>(R.id.option3Button)
        val option4Button = findViewById<Button>(R.id.option4Button)

        val question = questions[currentQuestionIndex]

        questionTextView.text = question.questionText
        option1Button.text = question.options[0]
        option2Button.text = question.options[1]
        option3Button.text = question.options[2]
        option4Button.text = question.options[3]

        option1Button.setOnClickListener { checkAnswer(0) }
        option2Button.setOnClickListener { checkAnswer(1) }
        option3Button.setOnClickListener { checkAnswer(2) }
        option4Button.setOnClickListener { checkAnswer(3) }
    }

    private fun checkAnswer(selectedOption: Int) {
        val question = questions[currentQuestionIndex]

        if (selectedOption == question.correctAnswer) {
            score++
            showToast("Correct!")
        } else {
            showToast("Wrong!")
        }

        currentQuestionIndex++
        if (currentQuestionIndex < questions.size) {
            showQuestion()
        } else {
            showResult()
        }
    }

    private fun showResult() {
        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra("SCORE_EXTRA", score)
        startActivity(intent)
        finish()
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
