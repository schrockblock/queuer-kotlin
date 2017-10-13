package com.gdi.queuer.models

import android.app.Activity
import android.content.Context
import android.text.TextUtils

/**
 * Created by ell on 10/12/17.
 */
class SessionManager {

    companion object {
        private val USER_PREFS = "USER"
        private val USER_ID_PREF = "USER_ID"
        private val USER_API_KEY_PREF = "USER_API_KEY"

        fun saveUser(context: Context, user: User) {
            val prefs = context.getSharedPreferences(USER_PREFS, Activity.MODE_PRIVATE)
            prefs.edit()
                    .putInt(USER_ID_PREF, user.id ?: 0)
                    .putString(USER_API_KEY_PREF, user.apiKey ?: "")
                    .apply()
        }

        fun loadUser(context: Context): User? {
            val prefs = context.getSharedPreferences(USER_PREFS, Activity.MODE_PRIVATE)
            val id = prefs.getInt(USER_ID_PREF, 0)
            val apiKey = prefs.getString(USER_API_KEY_PREF, "")

            if (id == 0  || TextUtils.isEmpty(apiKey)) return null

            return User(id, apiKey)
        }

        fun logout(context: Context) {
            val user = User(null, null)
            saveUser(context, user)
        }
    }
}