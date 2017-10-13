package com.gdi.queuer.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.gdi.queuer.R
import com.gdi.queuer.models.Project
import com.gdi.queuer.views.ProjectViewHolder

/**
 * Created by ell on 10/12/17.
 */
class ProjectsAdapter(var projects: List<Project>): RecyclerView.Adapter<ProjectViewHolder>() {

    override fun getItemCount(): Int = projects.size

    override fun onBindViewHolder(holder: ProjectViewHolder?, position: Int) {
        holder?.setProject(projects[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ProjectViewHolder {
        val inflater = LayoutInflater.from(parent?.context)
        val itemView = inflater.inflate(R.layout.item_project, parent, false)
        return ProjectViewHolder(itemView)
    }


}