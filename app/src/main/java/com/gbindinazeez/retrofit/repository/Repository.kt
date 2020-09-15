package com.gbindinazeez.retrofit.repository

import com.gbindinazeez.retrofit.api.RetrofitInstance
import com.gbindinazeez.retrofit.model.Post
import retrofit2.Response
import retrofit2.Retrofit

class Repository {

    suspend fun getPost(): Response<Post> {
        return RetrofitInstance.api.getPost()
    }

    suspend fun getPost2(number: Int): Response<Post> {
        return RetrofitInstance.api.getPost2(number)
    }

    suspend fun getCustomPosts(userId: Int): Response<List<Post>> {
        return RetrofitInstance.api.getCustomPost(userId)
    }
}