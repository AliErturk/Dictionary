package com.example.dictionaryapp

class Wordsdao  {


    fun allWords(da: DatabaseAssistant) : ArrayList<Words>{

        val wordList = ArrayList<Words>()
        val db = da.writableDatabase

        val cursor= db.rawQuery("SELECT*FROM kelimeler",null)

        while (cursor.moveToNext()){
            val word = Words(
                cursor.getInt(cursor.getColumnIndexOrThrow("kelime_id")),
                cursor.getString(cursor.getColumnIndexOrThrow("ingilizce")),
                cursor.getString(cursor.getColumnIndexOrThrow("turkce"))
            )
            wordList.add(word)
        }
        return wordList
    }

    fun findWords(da: DatabaseAssistant,searchingWord:String): ArrayList<Words>{
        val wordList = ArrayList<Words>()
        val db = da.writableDatabase

        val cursor= db.rawQuery("SELECT*FROM kelimeler WHERE ingilizce like '$searchingWord%'",null)

        while (cursor.moveToNext()){
            val word = Words(
                cursor.getInt(cursor.getColumnIndexOrThrow("kelime_id")),
                cursor.getString(cursor.getColumnIndexOrThrow("ingilizce")),
                cursor.getString(cursor.getColumnIndexOrThrow("turkce"))
            )
            wordList.add(word)
        }
        return wordList
    }
}