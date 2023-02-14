package com.itechnowizard.login_mvvm.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map


private val Context.dataStore : DataStore<Preferences> by preferencesDataStore(
    name = "app_pref"
)
class PrefUtils(private val context: Context) {

    companion object{
        val IS_USER_LOGIN = booleanPreferencesKey("IS_USER_LOGIN")
    }

    suspend fun saveLogin(isLogin : Boolean){
        context.dataStore.edit { preference ->
            preference[IS_USER_LOGIN] = isLogin
        }
    }

    val checkLogin : kotlinx.coroutines.flow.Flow<Boolean> = context.dataStore.data.map { preferences ->
         preferences[IS_USER_LOGIN] ?: false
    }


}