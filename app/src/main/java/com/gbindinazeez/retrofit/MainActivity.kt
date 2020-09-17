package com.gbindinazeez.retrofit

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.gbindinazeez.retrofit.adapter.MyAdapter
import com.gbindinazeez.retrofit.repository.Repository
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private val myAdapter by lazy { MyAdapter() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel= ViewModelProvider(this,viewModelFactory).get(MainViewModel::class.java)

        button.setOnClickListener {
            val myNumber = editText.text.toString()
            viewModel.getCustomPosts(Integer.parseInt(myNumber), "id", "asc")
            viewModel.myCustomPosts.observe(this, Observer { response ->
                if (response.isSuccessful) {
                    myAdapter.setData(response.body()!!)
                } else {
                    Toast.makeText(this, response.code(), Toast.LENGTH_SHORT).show()
                }
            })

            setupRecyclerview()
        }
    }

    private fun setupRecyclerview(){
        recyclerView.adapter = myAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}