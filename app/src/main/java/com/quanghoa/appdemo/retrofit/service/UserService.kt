package com.quanghoa.appdemo.retrofit.service

import com.quanghoa.appdemo.retrofit.model.User
import com.quanghoa.appdemo.retrofit.model.UserResponse
import okhttp3.Response
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface UserService{

    @GET("users")
    fun list(
        @Query("per_page") perPage: Int,
        @Query("page") page: Int): Call<UserResponse>


    @POST("users")
    fun create(@Body user: User): Call<ResponseBody>
}