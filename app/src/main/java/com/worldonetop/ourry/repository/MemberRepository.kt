package com.worldonetop.ourry.repository

import android.util.Log
import com.worldonetop.ourry.di.manager.network.onFail
import com.worldonetop.ourry.di.manager.network.onSuccess
import com.worldonetop.ourry.di.manager.network.transform
import com.worldonetop.ourry.model.remote.MemberTable
import com.worldonetop.ourry.model.domain.ApiResponse
import com.worldonetop.ourry.model.extension.toTable
import com.worldonetop.ourry.source.local.AccountDao
import com.worldonetop.ourry.source.remote.OurryAPI
import org.json.JSONObject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MemberRepository @Inject constructor(
    private val api: OurryAPI,
    private val dao: AccountDao
) {

    suspend fun login(): ApiResponse<Unit> {
        val input = dao.getInfo() ?: return ApiResponse.Failure.Fail("TEMP 유저 정보가 없음")

        return api.login(
            input.toTable().copy(id = null)
        ).transform {
            dao.saveInfo(
                input.copy(
                    token = it.getString("jwt")
                )
            )
        }
    }
    suspend fun login(email:String, pw: String): ApiResponse<String> {
        val output = api.login(MemberTable(
            email = email,
            password = pw
        ))
        return output.transform { it.getString("jwt") }
    }

    suspend fun signup(email: String, pw: String, nickname: String, phone: String): ApiResponse<MemberTable> {
        // TODO return domain model
        val input = MemberTable(
            email = email,
            password = pw,
            nickname = nickname,
            phone = phone
        )
        return api.createAccount(input).transform { output ->
            input.copy(
                id = output.id!!
            )
        }
    }
    suspend fun emailAuth(email: String, code:String? = null): ApiResponse<Unit> {
        return if(code == null)
            api.sendAuthCode(MemberTable(email = email))
        else
            api.emailAuth(JSONObject(mapOf(
                "email" to email,
                "code" to code,
            )))
    }

    suspend fun resetPw(email: String, pw: String, confirmPw: String): ApiResponse<Unit> {
        return api.pwReset(JSONObject(mapOf(
            "email" to email,
            "newPassword" to pw,
            "confirmPassword" to confirmPw
        )))
    }
}