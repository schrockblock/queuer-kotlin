package com.gdi.queuer.views

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.gdi.queuer.R
import com.gdi.queuer.models.Project
import kotlinx.android.synthetic.main.item_project.view.*

/**
 * Created by ell on 10/12/17.
 */
class ProjectViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    var nameTextView: TextView? = null

    init {
        nameTextView = itemView?.findViewById(R.id.tv_project_name)
    }

    fun setProject(project: Project) {
        nameTextView?.text = project.name
    }
}