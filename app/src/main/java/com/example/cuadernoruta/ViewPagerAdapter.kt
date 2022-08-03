package com.example.cuadernoruta

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ViewPagerAdapter(private val lista: List<Pagina>): RecyclerView.Adapter<PagViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PagViewHolder(layoutInflater.inflate(R.layout.item_pagina,parent,false))
    }

    override fun onBindViewHolder(holder: PagViewHolder, position: Int) {

        val item = lista[position]
        holder.render(item)

    }

    override fun getItemCount(): Int {
        return lista.size
    }


}