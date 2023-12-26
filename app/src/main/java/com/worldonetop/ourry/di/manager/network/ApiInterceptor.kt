package com.worldonetop.ourry.di.manager.network

import android.util.Log
import com.worldonetop.ourry.di.data.NetworkModule
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import org.json.JSONException
import org.json.JSONObject

class ApiInterceptor(
//    private val loginRepository: LoginRepository
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var response = chain.proceed(
            chain.request()
                .newBuilder()
//                .addHeader("Authorization", loginRepository.sessionToken)
                .build()
        )

        // TODO(세션 토큰 만료시 재로그인 로직)


        return try{
            val js = JSONObject(response.peekBody(2048).string())
            response
                .newBuilder()
                .message(js.getString("code"))
                .build()
        }catch (e: JSONException) {
            response
        }
    }
}