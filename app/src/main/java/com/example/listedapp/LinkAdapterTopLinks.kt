package com.example.listedapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.listedapp.databinding.AdapterLinksBinding
import java.text.SimpleDateFormat
import java.util.*

class LinkAdapterTopLinks:RecyclerView.Adapter<MainViewHolder>() {
    var linkList= mutableListOf<TopLinks>()

    fun setLinks(links:List<TopLinks>){
        this.linkList=links.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater=LayoutInflater.from(parent.context)
        val binding=AdapterLinksBinding.inflate(inflater,parent,false)
        return  MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val link=linkList[position]

        if(link.title!!.length>31){
            val linkUrl= link.title!!.substring(0,28)+"..."
            holder.binding.linkName.text = linkUrl

        }else {
            holder.binding.linkName.text = link.title
        }

        if(link.webLink!!.length>31){
            val linkUrl= link.webLink!!.substring(0,28)+"..."
            holder.binding.link.text = linkUrl
        }else {
            holder.binding.link.text = link.webLink
        }

        holder.binding.noClicks.text=link.totalClicks.toString()

        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        val outputFormat = SimpleDateFormat("MMMM d, yyyy")
        val parsedDate: Date = inputFormat.parse(link.createdAt)
        val formattedDate = outputFormat.format(parsedDate)

        holder.binding.date.text=formattedDate

        Glide.with(holder.itemView.context).load(link.originalImage).into(holder.binding.iconWebsite)

    }

    override fun getItemCount(): Int {
        return linkList.size
    }
}


class LinkAdapterRecentLinks:RecyclerView.Adapter<MainViewHolder>() {
    var linkList= mutableListOf<RecentLinks>()

    fun setLinks(links:List<RecentLinks>){
        this.linkList=links.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater=LayoutInflater.from(parent.context)
        val binding=AdapterLinksBinding.inflate(inflater,parent,false)
        return  MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val link=linkList[position]

        if(link.title!!.length>31){
            val linkUrl= link.title!!.substring(0,28)+"..."
            holder.binding.linkName.text = linkUrl
        }else {
            holder.binding.linkName.text = link.title
        }


        if(link.webLink!!.length>31){
            val linkUrl= link.webLink!!.substring(0,28)+"..."
            holder.binding.link.text = linkUrl
        }else {
            holder.binding.link.text = link.webLink
        }


        holder.binding.noClicks.text=link.totalClicks.toString()

        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        val outputFormat = SimpleDateFormat("MMMM d, yyyy")
        val parsedDate: Date = inputFormat.parse(link.createdAt)
        val formattedDate = outputFormat.format(parsedDate)

        holder.binding.date.text=formattedDate

        Glide.with(holder.itemView.context).load(link.originalImage).into(holder.binding.iconWebsite)

    }

    override fun getItemCount(): Int {
        return linkList.size
    }
}


class MainViewHolder(val binding: AdapterLinksBinding) : RecyclerView.ViewHolder(binding.root) {

}