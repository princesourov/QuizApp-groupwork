package com.epikason.groupwork

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.epikason.groupwork.databinding.ActivityPlayBinding

class PlayActivity : AppCompatActivity() {
    private lateinit var viewModel: PlayViewModel

    lateinit var binding: ActivityPlayBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityPlayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(PlayViewModel::class.java)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        showCurrentQuestion()



        binding.nextBtn.setOnClickListener {

            val selectedIndex =
                binding.radioGroupBtn
                    .indexOfChild(
                        findViewById(
                            binding.radioGroupBtn.checkedRadioButtonId
                        ))

            if (selectedIndex !=-1){
                viewModel.checkAnswer(selectedIndex)
                setNEXTQuestion()
            }else{
                Toast.makeText(this,"Please Select any Option", Toast.LENGTH_LONG).show()
            }

        }

    }

    private fun showCurrentQuestion() {
        val currentQuestion = viewModel.getCurrentQuestion()

        binding.questionTv.text =currentQuestion.question
        binding.option1.text = currentQuestion.options[0]
        binding.option2.text = currentQuestion.options[1]
        binding.option3.text = currentQuestion.options[2]
        binding.option4.text = currentQuestion.options[3]
        binding.radioGroupBtn.clearCheck()
    }

    private fun setNEXTQuestion(){

        val nextQuestion = viewModel.getNextQuestion()

        if (nextQuestion!=null){
            showCurrentQuestion()
        }else{
            val resulIntent = Intent(this@PlayActivity, ResultActivity::class.java)
            resulIntent.putExtra("score",viewModel.getScore())
            startActivity(resulIntent)
        }


    }
}