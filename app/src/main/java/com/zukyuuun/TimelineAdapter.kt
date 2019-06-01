package com.zukyuuun

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

class TimelineAdapter(val datasource: List<String>): RecyclerView.Adapter<TimelineViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimelineViewHolder {
        return TimelineViewHolder(DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_timeline,
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: TimelineViewHolder, position: Int) {
        holder.binding.text = datasource[position]
    }

    override fun getItemCount(): Int = datasource.size

}