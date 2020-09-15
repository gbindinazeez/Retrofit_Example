package com.gbindinazeez.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.gbindinazeez.retrofit.repository.Repository
import kotlinx.android.synthetic.main.activity_main.*

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel= ViewModelProvider(this,viewModelFactory).get(MainViewModel::class.java)
        viewModel.getPost()
/*        viewModel.myResponse.observe(this, Observer { response ->
            if (response.isSuccessful) {
                Log.d(TAG, response.body()?.userId.toString())
                Log.d(TAG, response.body()?.id.toString())
                textView.text = response.body()?.title
                *//*Log.d(TAG, response.body()?.title)*//*
                Log.d(TAG, response.body()?.body)
            }else{
                Log.d(TAG,response.errorBody().toString())
                textView.text = response.code().toString()
            }
        })*/

/*        button.setOnClickListener {
            val myNumber = editText.text.toString()
            viewModel.getPost2(Integer.parseInt(myNumber))
            viewModel.myResponse2.observe(this, Observer { response ->
                if (response.isSuccessful) {
                    textView.text = response.body()?.title
                }else{
                    Log.d(TAG,response.errorBody().toString())
                    textView.text = response.code().toString()
                }
            })
        }*/

        button.setOnClickListener {
            val myNumber = editText.text.toString()
            viewModel.getCustomPosts(Integer.parseInt(myNumber))
            viewModel.myCustomPosts.observe(this, Observer { response ->
                if (response.isSuccessful) {
                    response.body()?.forEach {
                        Log.d(TAG,it.userId.toString())
                        Log.d(TAG,it.id.toString())
                        Log.d(TAG,it.title)
                        Log.d(TAG,it.body)
                        Log.d(TAG,"----------------")

                    }
                }else{
                    Log.d(TAG,response.errorBody().toString())
                    textView.text = response.code().toString()
                }
            })
        }
    }
}