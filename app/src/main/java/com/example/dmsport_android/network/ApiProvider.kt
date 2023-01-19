package com.example.dmsport_android.network

val loginApi: LoginApi by lazy {
    RetrofitClient.getRetrofit().create(LoginApi::class.java)
}

val registerApi: RegisterApi by lazy {
    RetrofitClient.getRetrofit().create(RegisterApi::class.java)
}

val myPageApi: MyPageApi by lazy {
    RetrofitClient.getRetrofit().create(MyPageApi::class.java)
}

val voteApi : VoteApi by lazy {
    RetrofitClient.getRetrofit().create(VoteApi::class.java)
}

val changePwApi : ChangePwApi by lazy {
    RetrofitClient.getRetrofit().create(ChangePwApi::class.java)
}

val noticeApi: NoticeApi by lazy {
    RetrofitClient.getRetrofit().create(NoticeApi::class.java)
}

val refreshApi : RefreshApi by lazy {
    RetrofitClient.getRetrofit().create(RefreshApi::class.java)
}