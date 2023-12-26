package com.worldonetop.ourry.model.domain

import android.util.Log

sealed interface ApiResponse<out T> {
    data class Success<T>(val data: T) : ApiResponse<T> // 요청 성공

    sealed interface Failure<T>: ApiResponse<T> {
        data class Fail<T>(val code: String, val message: String = "") : Failure<T>
        data class Exception<T>(val exception: Throwable) : Failure<T> {
            init {
                Log.d("asd", "#################### Network Exception ####################")
                Log.e("asd", Log.getStackTraceString(exception));
                Log.d("asd", "###########################################################")
            }
        }
    }
}