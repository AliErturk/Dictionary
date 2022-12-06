package com.example.dictionaryapp

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseAssistant(context: Context) : SQLiteOpenHelper(context,"sozluk.sqlite",null,1) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE IF NOT EXISTS kelimeler(" +
                "kelime_id  INTEGER PRIMARY KEY AUTOINCREMENT," +
                "turkce TEXT," +
                "ingilizce TEXT)  ")
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL("DROP TABLE IF EXISTS kelimeler")
        onCreate(db)
    }
}