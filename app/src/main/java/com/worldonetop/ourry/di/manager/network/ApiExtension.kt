package com.worldonetop.ourry.di.manager.network

import com.worldonetop.ourry.model.domain.ApiResponse


@JvmSynthetic
inline fun <T> ApiResponse<T>.onSuccess(
    crossinline action: ApiResponse.Success<T>.() -> Unit,
): ApiResponse<T> {
    if (this is ApiResponse.Success)
        action(this)
    return this
}

@JvmSynthetic
inline fun <T> ApiResponse<T>.onFail(
    crossinline action: ApiResponse.Failure<T>.() -> Unit,
): ApiResponse<T> {
    if(this is ApiResponse.Failure)
        action(this)
    return this
}

@JvmSynthetic
suspend inline fun <T> ApiResponse<T>.suspendOnSuccess(
    crossinline action: suspend ApiResponse.Success<T>.() -> Unit,
): ApiResponse<T> {
    if (this is ApiResponse.Success)
        action(this)
    return this
}

@JvmSynthetic
suspend inline fun <T> ApiResponse<T>.suspendOnFail(
    crossinline action: suspend ApiResponse.Failure<T>.() -> Unit,
): ApiResponse<T> {
    if(this is ApiResponse.Failure)
        action(this)
    return this
}

@JvmSynthetic
inline fun <T, R> ApiResponse<T>.transform(
    transform: (T) -> R,
): ApiResponse<R> {
    return when(this){
        is ApiResponse.Success ->
            try {
                ApiResponse.Success(transform(data))
            }catch (e: Throwable){
                ApiResponse.Failure.Exception(e)
            }
        is ApiResponse.Failure.Fail -> ApiResponse.Failure.Fail(code, message)
        is ApiResponse.Failure.Exception -> ApiResponse.Failure.Exception(exception)
    }
}