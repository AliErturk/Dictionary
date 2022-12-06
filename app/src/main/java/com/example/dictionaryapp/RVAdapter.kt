package com.example.dictionaryapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView

class RVAdapter(private val context: Context,private val wordList: List<Words>) :
                RecyclerView.Adapter<RVAdapter.CardViewElements>(){

    inner class CardViewElements(view: View) : RecyclerView.ViewHolder(view) {

        var textViewEnglish: TextView
        var textViewTurkish: TextView
        var cardViewWords: CardView
        init {
            textViewEnglish = view.findViewById(R.id.textViewEnglish)
            textViewTurkish= view.findViewById(R.id.textViewTurkish)
            cardViewWords= view.findViewById(R.id.cardViewWords)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewElements {
        val design = LayoutInflater.from(context).inflate(R.layout.card_view_words,parent,false)
        return CardViewElements(design)
    }

    override fun onBindViewHolder(holder: CardViewElements, position: Int) {
        val word= wordList[position]
        holder.textViewEnglish.text = word.ingilizce
        holder.textViewTurkish.text= word.turkce
        holder.cardViewWords.setOnClickListener {
            val intent = Intent(context,DetailActivity::class.java)
            intent.putExtra("word",word)

            context.startActivity(intent)

        }
    }



    override fun getItemCount(): Int {
        return wordList.size
    }
}