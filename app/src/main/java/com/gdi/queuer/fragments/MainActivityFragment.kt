package com.gdi.queuer.fragments

import android.support.v4.app.Fragment
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.volley.RequestQueue
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.gdi.queuer.R
import com.gdi.queuer.adapters.ProjectsAdapter
import com.gdi.queuer.models.Project
import com.gdi.queuer.models.ServerConstants
import com.gdi.queuer.models.SessionManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONArray

/**
 * A placeholder fragment containing a simple view.
 */
class MainActivityFragment : Fragment() {
    var recyclerView: RecyclerView? = null

    val projects = ArrayList<Project>()

    var queue: RequestQueue? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        recyclerView = view.findViewById(R.id.rv_projects)

        val manager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        recyclerView?.layoutManager = manager

        val user = SessionManager.loadUser(activity)!!
        val request = object : JsonArrayRequest(ServerConstants.USERS_URL + "/" + user.id + "/projects",
                { response: JSONArray? ->
                    val listOfProjects = object : TypeToken<List<Project>>() {}.type
                    val projects = Gson().fromJson<ArrayList<Project>>(response.toString(), listOfProjects)

                    val projectAdapter = ProjectsAdapter(projects)
                    recyclerView?.adapter = projectAdapter
                }, { error: VolleyError? ->
            error?.printStackTrace()
        }) {
            override fun getHeaders(): Map<String, String> {
                val params = HashMap<String, String>()
                params.put("X-Qer-Authorization", user.apiKey ?: "")
                return params
            }
        }

        queue = Volley.newRequestQueue(activity)
        queue?.add(request)

        return view
    }
}
