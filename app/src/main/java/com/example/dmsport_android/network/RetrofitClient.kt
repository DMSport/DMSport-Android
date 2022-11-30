package com.example.dmsport_android.network

import androidx.lifecycle.ViewModelProvider
import com.example.dmsport_android.BuildConfig
import com.example.dmsport_android.feature.refresh.*
import com.example.dmsport_android.feature.register.viewmodel.RegisterViewModel
import com.example.dmsport_android.feature.register.viewmodel.factory.RegisterViewModelFactory
import com.example.dmsport_android.util.ACCESS_TOKEN
import com.example.dmsport_android.util.REFRESH_TOKEN
import com.example.dmsport_android.util.UNAUTHORIZED
import kotlinx.coroutines.*
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private lateinit var retrofit: Retrofit

    private val refreshRepository by lazy {
        RefreshRepository()
    }

    fun getRetrofit(): Retrofit {
        retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(
                        RequestInterceptor(refreshRepository)
                    )
                    .addInterceptor(
                        ResponseInterceptor(refreshRepository)
                    )
                    .build()
            )
            .build()
        return retrofit
    }
}

class RequestInterceptor(
    private val refreshRepository: RefreshRepository
) : Interceptor {

    override fun intercept(
        chain: Interceptor.Chain,
    ): Response {
        CoroutineScope(Dispatchers.IO).launch {
            kotlin.runCatching {
                refreshRepository.getRefreshToken()
            }.onSuccess{
                if(it.isSuccessful) {
                    ACCESS_TOKEN = it.body()!!.access_token
                }
            }
        }
        val addAuthorization = chain.request().newBuilder()
            .addHeader(
                "Authorization",
                "Bearer $ACCESS_TOKEN",
            )
            .build()

        return chain.proceed(addAuthorization)
    }
}

class ResponseInterceptor(
    private val refreshRepository: RefreshRepository
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request()
        val response = chain.proceed(request)

        when (response.code()) {
            UNAUTHORIZED -> {
                CoroutineScope(Dispatchers.IO).launch {
                    kotlin.runCatching {
                        refreshRepository.getRefreshToken()
                    }.onSuccess{
                        if(it.isSuccessful) {
                            ACCESS_TOKEN = "Bearer ${it.body()!!.access_token}"
                        }
                    }
                }
            }
        }
        return response
    }
}