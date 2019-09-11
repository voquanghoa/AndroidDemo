package com.quanghoa.appdemo.retrofit

import okhttp3.Interceptor
import okhttp3.Response

class RetrofitInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        //Xử lý request trước khi gửi request

        val response = chain.proceed(request)

        //Xử lý response sau khi request

        return response
    }
}