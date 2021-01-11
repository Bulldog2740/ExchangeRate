package com.web.exchangerate.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.web.exchangerate.R
import com.web.exchangerate.data.model.NBUBankRate

/**
 *Created by Yehor Kudimov on 07.01.2021.
 */
class NBUAdapter(
    private var listener: Listener
) : ListAdapter<NBUBankRate, NBUAdapter.NBUViewHolder>(diff) {

    companion object {
        val diff = object : DiffUtil.ItemCallback<NBUBankRate>() {
            override fun areItemsTheSame(
                oldItem: NBUBankRate,
                newItem: NBUBankRate
            ): Boolean {
                return oldItem.id == newItem.id && oldItem.name == newItem.name
            }

            override fun areContentsTheSame(
                oldItem: NBUBankRate,
                newItem: NBUBankRate
            ): Boolean {
                return oldItem.rate == newItem.rate && oldItem.nameEng == newItem.nameEng
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NBUViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_nbu_list_item, parent, false)
        return NBUViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: NBUViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) holder.bind(item, listener)
    }

    class NBUViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private var nbuBaseCurrency: TextView? = view.findViewById(R.id.nameNBU)
        private var nbuEnglishCurrency: TextView? = view.findViewById(R.id.nameEngNBU)
        private var buy: TextView? = view.findViewById(R.id.buyNBU)
        fun bind(nbu: NBUBankRate, listener: Listener) {
            nbuBaseCurrency?.text = nbu.name
            nbuEnglishCurrency?.text = nbu.nameEng
            buy?.text = nbu.rate.toString()

            itemView.setOnClickListener { listener.onClick(nbu) }
        }
    }

    interface Listener {
        fun onClick(nbu: NBUBankRate)
    }
}

