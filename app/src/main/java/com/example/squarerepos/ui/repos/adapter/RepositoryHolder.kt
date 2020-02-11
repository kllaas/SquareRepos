package com.example.squarerepos.ui.repos.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.squarerepos.domain.repos.RepositoryView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_repo.view.*


class RepositoryHolder(
    override val containerView: View
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(repository: RepositoryView) {
        itemView.title.text = repository.title
        itemView.description.text = repository.description
    }

}
