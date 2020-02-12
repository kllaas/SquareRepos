package com.example.squarerepos.ui.repos.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.squarerepos.R
import com.example.squarerepos.domain.repos.RepositoryView


class ReposAdapter : ListAdapter<RepositoryView, RepositoryHolder>(RepositoryDiff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryHolder {
        return RepositoryHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_repo, parent, false))
    }

    override fun onBindViewHolder(holder: RepositoryHolder, position: Int) {
        holder.bind(getItem(position))
    }

}

object RepositoryDiff : DiffUtil.ItemCallback<RepositoryView>() {

    override fun areItemsTheSame(oldItem: RepositoryView, newItem: RepositoryView): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: RepositoryView, newItem: RepositoryView): Boolean {
        return oldItem == newItem
    }
}
