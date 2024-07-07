package com.example.happybirthday

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.happybirthday.databinding.ActivityBirthDayBinding
import com.example.happybirthday.databinding.ActivityMainBinding

class BirthDay : AppCompatActivity() {
    private lateinit var binding: ActivityBirthDayBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityBirthDayBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val intent = intent
        val str = intent.getStringExtra("To wish")
        val str1 = intent.getStringExtra("Wisher")
        binding.textView.text = str
        binding.textView2.text = str1

        binding.imageView.setOnClickListener{

        }
    }
}