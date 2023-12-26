package com.worldonetop.ourry.source.local

import android.content.SharedPreferences
import android.util.Log
import androidx.core.content.edit
import com.google.gson.Gson
import com.worldonetop.ourry.model.local.AccountEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AccountDao @Inject constructor(
    private val pref: SharedPreferences,
) {
    private val ROOT_KEY = "ACCOUNT"
    private val gson = Gson()

    init {
        Log.d("asd","@@@@@@@@@@@@@@@@ da0  create")
    }

    fun deleteInfo(){
        pref.edit {
            putString(ROOT_KEY, "")
        }
    }

    fun saveInfo(account: AccountEntity){
        pref.edit {
            putString(ROOT_KEY, gson.toJson(account))
        }
    }
    fun getInfo(): AccountEntity? {
        return try {
            val info = pref.getString(ROOT_KEY, "")
            gson.fromJson(info, AccountEntity::class.java)
        }catch (e: Exception){
            null
        }
    }

    fun isLoggedIn() = getInfo() != null

}