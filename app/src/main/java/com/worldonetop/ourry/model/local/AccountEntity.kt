package com.worldonetop.ourry.model.local

import com.google.gson.annotations.SerializedName

data class AccountEntity (
    @SerializedName("id")
    val id: Long,
    @SerializedName("email")
    val email: String,
    @SerializedName("pw")
    val pw: String,
    @SerializedName("token")
    val token: String,
)