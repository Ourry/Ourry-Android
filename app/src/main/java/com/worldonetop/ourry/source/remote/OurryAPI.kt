package com.worldonetop.ourry.source.remote

import com.worldonetop.ourry.model.remote.MemberTable
import com.worldonetop.ourry.model.domain.ApiResponse
import org.json.JSONObject
import retrofit2.http.Body
import retrofit2.http.POST

interface OurryAPI {
    /******************
     *     Member     *
     ******************/

    /** 회원가입 **/
    @POST("member/createAccount")
    suspend fun createAccount(
        @Body member: MemberTable
    ): ApiResponse<MemberTable>

    /** 로그인 **/
    @POST("member/memberLogin")
    suspend fun login(
        @Body member: MemberTable
    ): ApiResponse<JSONObject>

    /** 인증코드 발송 **/
    @POST("member/sendAuthenticationCode")
    suspend fun sendAuthCode(
        @Body member: MemberTable
    ): ApiResponse<Unit>

    /** 인증코드 비교 **/
    @POST("member/emailAuthentication")
    suspend fun emailAuth(
        @Body emailAndCode: JSONObject
    ): ApiResponse<Unit>

    /** 비번 재설정 **/
    @POST("member/passwordReset")
    suspend fun pwReset(
        @Body emailAndPw: JSONObject
    ): ApiResponse<Unit>
}