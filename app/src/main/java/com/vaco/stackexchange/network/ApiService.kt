package com.vaco.stackexchange.network

import com.vaco.stackexchange.mvvm.model.Questions
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("questions")
    suspend fun getQuestions(@Query("order") order: String, @Query("sort") sort: String, @Query("site") site: String) : Questions

}