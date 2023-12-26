package com.worldonetop.ourry.di.manager.network

import com.worldonetop.ourry.model.domain.ApiResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ApiCall<T>(private val call: Call<T>): Call<ApiResponse<T>> {
    override fun enqueue(callback: Callback<ApiResponse<T>>) {
        call.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                callback.onResponse(this@ApiCall,
                    if(!response.isSuccessful || (response.code() / 100) != 2 || response.body() == null)
                        Response.success(
                            ApiResponse.Failure.Exception(Exception("Unknown"))
                        )
                    else if(response.message() != "OK")
                        Response.success(
                            ApiResponse.Failure.Fail(response.message())
                        )
                    else
                        Response.success(
                            ApiResponse.Success(response.body()!!)
                        )
                )
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                callback.onResponse(this@ApiCall,
                    Response.success<ApiResponse<T>?>(ApiResponse.Failure.Exception(t))
                        .also {
                            CoroutineScope(Dispatchers.Main).launch{
                                // TODO error global handling (Ex: toast)
                            }
                        }
                )
            }
        })
    }

    override fun clone(): Call<ApiResponse<T>> = ApiCall(call.clone())

    override fun execute(): Response<ApiResponse<T>> {
        throw UnsupportedOperationException("unsupported method")
    }

    override fun isExecuted(): Boolean = call.isExecuted

    override fun cancel() = call.cancel()

    override fun isCanceled(): Boolean = call.isCanceled

    override fun request(): Request = call.request()

    override fun timeout(): Timeout {
        // TODO : Internet Check Status
        return call.timeout()
    }
}