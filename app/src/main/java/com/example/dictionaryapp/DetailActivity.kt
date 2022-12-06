package com.example.dictionaryapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.dictionaryapp.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var design: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        design= DataBindingUtil.setContentView(this,R.layout.activity_detail)

        val word = intent.getSerializableExtra("word") as Words


        design.textViewEngDetail.text = word.ingilizce
        design.textViewTurkDetail.text = word.turkce
    }
}