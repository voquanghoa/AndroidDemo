package com.quanghoa.appdemo.storage.internal.models

import com.google.gson.annotations.SerializedName

data class MyContact(
    @SerializedName("first_name") val firstName: String,
    @SerializedName("last_name") val lastName: String,
    @SerializedName("phone_number") val phoneNumber: String,
    @SerializedName("notes") val notes: String
)