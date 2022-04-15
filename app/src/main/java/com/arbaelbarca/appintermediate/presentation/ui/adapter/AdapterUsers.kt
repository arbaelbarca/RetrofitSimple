package com.arbaelbarca.appintermediate.presentation.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arbaelbarca.appintermediate.data.datasource.local.response.ResponseListUsers
import com.arbaelbarca.appintermediate.databinding.LayoutItemUsersBinding
import com.arbaelbarca.appintermediate.utils.ViewBindingVH
import com.bumptech.glide.Glide

class AdapterUsers(
    val mutableListUsers: MutableList<ResponseListUsers.Data>
) : RecyclerView.Adapter<ViewBindingVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewBindingVH {
        return ViewBindingVH.create(parent, LayoutItemUsersBinding::inflate)
    }

    override fun onBindViewHolder(holder: ViewBindingVH, position: Int) {
        val dataItem = mutableListUsers[position]
        (holder.binding as LayoutItemUsersBinding).apply {
            tvItemUsers.text = dataItem.firstName
            Glide.with(holder.itemView.context)
                .load(dataItem.avatar)
                .into(imgItemUsers)
        }
    }

    override fun getItemCount(): Int {
        return mutableListUsers.size
    }
}