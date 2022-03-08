package com.vaco.stackexchange.network

import com.vaco.stackexchange.misc.Constant
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiInstance {

    companion object {
        fun create() : Retrofit {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constant.BASE_URL)
                .build()
        }
    }
}