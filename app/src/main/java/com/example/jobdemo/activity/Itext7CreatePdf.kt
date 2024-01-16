package com.example.jobdemo.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.jobdemo.R
import com.example.jobdemo.databinding.ActivityItext7CreatePdfBinding

class Itext7CreatePdf : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityItext7CreatePdfBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}