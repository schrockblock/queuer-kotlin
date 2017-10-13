package com.gdi.queuer.models

/**
 * Created by ell on 10/10/17.
 */
class ServerConstants {

    companion object {
        val BASE_URL = "https://queuer-production.herokuapp.com/api/v1/"
        val SESSION_URL = BASE_URL + "session"
        val USERS_URL = BASE_URL + "users"
    }
}