package com.worldonetop.ourry.model.remote

import com.google.gson.annotations.SerializedName

data class MemberTable (
    @SerializedName("memberid")
    val id:Long? = null,
    @SerializedName("email")
    val email:String? = null,
    @SerializedName("password")
    val password : String? = null,
    @SerializedName("nickname")
    val nickname : String? = null,
    @SerializedName("phone")
    val phone : String? = null
)