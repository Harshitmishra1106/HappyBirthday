package com.example.happybirthday

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.happybirthday.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btn.setOnClickListener{
            val wish = binding.wish.text.toString()
            val wisher = binding.wisher.text.toString()
            val intent = Intent(applicationContext,BirthDay::class.java)
            intent.putExtra("To wish",wish)
            intent.putExtra("Wisher",wisher)
            startActivity(intent)
        }
    }
}