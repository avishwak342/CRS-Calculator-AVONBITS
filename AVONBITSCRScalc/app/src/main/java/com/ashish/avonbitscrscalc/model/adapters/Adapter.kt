package com.ashish.avonbitscrscalc.model.adapters

import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.ashish.avonbitscrscalc.R


class Adapter<T, V>(
    var listData: List<T>,
    private val binding: (ViewGroup) -> V,
    private val view: (V) -> View,
    private val unit: (T, V) -> Unit): RecyclerView.Adapter<Adapter.Holder<T, V>>(){

    val update = { list: List<T> ->
        listData = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): Holder<T, V> = with(binding(p0)){ Holder(view(this), this, unit) }

    override fun getItemCount(): Int = listData.size

    override fun onBindViewHolder(p0: Holder<T, V>, p1: Int) = p0.setData(listData[p1])

    class Holder<T, V>(itemView: View, private val binding: V, private val unit: (item: T, view: V) -> Unit): RecyclerView.ViewHolder(itemView){

        val setData = { item: T ->
            itemView.animation = AnimationUtils.loadAnimation(itemView.context, R.anim.show)
            unit(item, binding)
        }
    }
}

