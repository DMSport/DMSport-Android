package com.example.dmsport_android.network

import com.example.dmsport_android.BuildConfig
import com.example.dmsport_android.util.ACCESS_TOKEN
import com.example.dmsport_android.util.REFRESH_TOKEN
import com.example.dmsport_android.util.UNAUTHORIZED
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private lateinit var retrofit: Retrofit

    fun getRetrofit(): Retrofit {
        retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(
                        RequestInterceptor()
                    )
                    .addInterceptor(
                        ResponseInterceptor()
                    )
                    .build()
            )
            .build()
        return retrofit
    }
}

class RequestInterceptor : Interceptor {
    private lateinit var request: Request
    override fun intercept(chain: Interceptor.Chain): Response {

        val ignorePath = arrayListOf(
            "/users/auth",
            "/users/mail/duplicate",
            "/users/mail/signup",
            "/users",
            "/users/mail/find",
            "/users/password",
        )
        request = if (ignorePath.contains(chain.request().url().encodedPath()))
            chain.request().newBuilder().build()
        else
            chain.request().newBuilder().addHeader(
                "Authorization",
                "Bearer $ACCESS_TOKEN",
            ).build()


        return chain.proceed(request)
    }

}

class ResponseInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request()
        val response = chain.proceed(request)

        if(response.code() == UNAUTHORIZED){
            CoroutineScope(Dispatchers.IO).launch {
                ACCESS_TOKEN =
                    "Bearer ${refreshApi.getRefreshToken(REFRESH_TOKEN).body()!!.access_token}"
            }
        }

        return response
    }
}