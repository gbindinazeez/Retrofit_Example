package com.gbindinazeez.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.gbindinazeez.retrofit.adapter.MyAdapter
import com.gbindinazeez.retrofit.repository.Repository
import kotlinx.android.synthetic.main.activity_main.*

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private val myAdapter by lazy { MyAdapter() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel= ViewModelProvider(this,viewModelFactory).get(MainViewModel::class.java)
        viewModel.getCustomPosts(2,"id","desc")
        viewModel.myCustomPosts.observe(this, Observer {response ->
            if (response.isSuccessful){
                myAdapter.setData(response.body()!!)
            } else{
                Toast.makeText(this,response.code(),Toast.LENGTH_SHORT).show()
            }
        })


        setupRecyclerview()
    }

    private fun setupRecyclerview(){
        recyclerView.adapter = myAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}