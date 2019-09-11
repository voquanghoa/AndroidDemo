package com.quanghoa.appdemo.retrofit.model

import com.google.gson.annotations.SerializedName

data class UserResponse(
    val page: Int,
    @SerializedName("per_page") val perPage: Int,
    val total: Int,
    @SerializedName("total_page")val pages: Int,
    val data: List<User>
)