package com.example.dmsport_android.network

import com.example.dmsport_android.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object ApiProvider {
    private val retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val loginApi : LoginApi by lazy {
        retrofit.create(LoginApi::class.java)
    }

    val registerApi : RegisterApi by lazy {
        retrofit.create(RegisterApi::class.java)
    }

    val myPageApi : MyPageApi by lazy {
        retrofit.create(MyPageApi::class.java)
    }


}