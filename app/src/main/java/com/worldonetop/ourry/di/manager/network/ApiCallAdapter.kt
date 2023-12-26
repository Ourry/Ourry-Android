package com.worldonetop.ourry.di.manager.network

import com.worldonetop.ourry.model.domain.ApiResponse
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

internal class ApiCallAdapter (
    private val resultType: Type,
) : CallAdapter<Type, Call<ApiResponse<Type>>> {

    override fun responseType() = resultType

    override fun adapt(call: Call<Type>) = ApiCall(call)

    internal class ApiCallAdapterFactory: CallAdapter.Factory() {
        override fun get(
            returnType: Type,
            annotations: Array<Annotation>,
            retrofit: Retrofit
        ): CallAdapter<*, *>? {
            if (Call::class.java != getRawType(returnType)) return null
            check(returnType is ParameterizedType)

            val responseType = getParameterUpperBound(0, returnType)
            if (getRawType(responseType) != ApiResponse::class.java) return null
            check(responseType is ParameterizedType)

            val successType = getParameterUpperBound(0, responseType)

            return ApiCallAdapter(successType)
        }
    }
}