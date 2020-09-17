package com.gbindinazeez.retrofit

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.gbindinazeez.retrofit.repository.Repository

private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
//    private val myAdapter by lazy { MyAdapter() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel= ViewModelProvider(this,viewModelFactory).get(MainViewModel::class.java)

//        val myPost = Post(2,2,"gbindinazeez","Android Developer Hotshot")
    viewModel.getPost("1112222")
            viewModel.myResponse.observe(this, Observer { response ->
                if (response.isSuccessful) {
                    Log.d(TAG, response.body().toString())
                    Log.d(TAG, response.code().toString())
                    Log.d(TAG, response.headers().toString())
                } else {
                    Toast.makeText(this, response.code(), Toast.LENGTH_SHORT).show()
                }
            })

        }

/*    private fun setupRecyclerview(){
        recyclerView.adapter = myAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }*/
}