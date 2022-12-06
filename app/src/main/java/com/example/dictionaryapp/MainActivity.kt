package com.example.dictionaryapp

import android.database.sqlite.SQLiteException
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dictionaryapp.databinding.ActivityMainBinding
import com.info.sqlitekullanimihazirveritabani.DatabaseCopyHelper

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private lateinit var design : ActivityMainBinding
    private lateinit var rvAdapter: RVAdapter
    private lateinit var wordList: ArrayList<Words>
    private lateinit var da : DatabaseAssistant
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        design = DataBindingUtil.setContentView(this,R.layout.activity_main)

        design.toolbar.title = "Dictionary App"
        setSupportActionBar(design.toolbar)

        copyDatabase()


        da = DatabaseAssistant(this)
        wordList = Wordsdao().allWords(da)

        design.recyclerView.setHasFixedSize(true)
        design.recyclerView.layoutManager= LinearLayoutManager(this)

        rvAdapter = RVAdapter(this,wordList)
        design.recyclerView.adapter = rvAdapter

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu,menu)
        val item = menu?.findItem(R.id.action_search)
        val searchView = item?.actionView as SearchView
        searchView.setOnQueryTextListener(this)
        return super.onCreateOptionsMenu(menu)
    }

    fun copyDatabase(){
        val dbCopyHelper = DatabaseCopyHelper(this)

        try {
            dbCopyHelper.createDataBase()
        }catch (e : SQLiteException){
            Error("Unable to create Database")
        }

        try {
            dbCopyHelper.openDataBase()
        }catch (e : SQLiteException){
            Error("Unable to open Database")
        }
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        search(query)
        return true
    }

    override fun onQueryTextChange(newText: String): Boolean {
        search(newText)
        return true
    }
    fun search(query : String){
        wordList= Wordsdao().findWords(da,query)

        rvAdapter= RVAdapter(this,wordList)

        design.recyclerView.adapter=rvAdapter

    }
}