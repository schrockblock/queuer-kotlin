package com.gdi.queuer.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.gdi.queuer.R
import com.gdi.queuer.models.ServerConstants
import com.gdi.queuer.models.SessionManager
import com.gdi.queuer.models.User
import com.google.gson.Gson
import org.json.JSONObject

/**
 * Created by ell on 10/10/17.
 */
class LoginActivity : AppCompatActivity() {
    var usernameEditText: EditText? = null
    var passwordEditText: EditText? = null
    var loginButton: Button? = null

    var queue: RequestQueue? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        usernameEditText = findViewById(R.id.et_username)
        passwordEditText = findViewById(R.id.et_password)
        loginButton = findViewById(R.id.btn_login)

        queue = Volley.newRequestQueue(this)

        loginButton?.setOnClickListener({
            attemptLogin()
        })

        val user = SessionManager.loadUser(this)
        if (user != null) {
            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
        }
    }

    fun attemptLogin(){
        val username = usernameEditText?.text
        val password = passwordEditText?.text

        val json = JSONObject()
        json.put("username", username)
        json.put("password", password)

        val request = JsonObjectRequest(Request.Method.POST, ServerConstants.SESSION_URL,
                json, { response ->
            val user = Gson().fromJson(response.toString(), User::class.java)
            user.apiKey = response.getString("api_key")

            SessionManager.saveUser(this@LoginActivity, user)

            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
        }, { error ->
            error.printStackTrace()
        })
        queue?.add(request)
    }
}