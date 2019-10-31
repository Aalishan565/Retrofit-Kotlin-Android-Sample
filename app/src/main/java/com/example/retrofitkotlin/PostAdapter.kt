package com.example.retrofitkotlin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row_item.view.*

class PostAdapter(val item: List<Post>, val context: Context) :
    RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.row_item, parent, false))
    }

    override fun getItemCount(): Int {
        return item.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvUserId.text = item.get(position).userId.toString()
        holder.tvTitle.text = item.get(position).title

    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvUserId = view.tvUserId;
        val tvTitle = view.tvTitle;

    }
}