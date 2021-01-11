package com.web.exchangerate.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.web.exchangerate.R
import com.web.exchangerate.data.model.PrivatBankRate

/**
 *Created by Yehor Kudimov on 06.01.2021.
 */

class PrivatBankAdapter(
    private var listener: Listener
) : ListAdapter<PrivatBankRate, PrivatBankAdapter.PrivatViewHolder>(diff) {
    companion object {
        val diff = object : DiffUtil.ItemCallback<PrivatBankRate>() {
            override fun areItemsTheSame(
                oldItem: PrivatBankRate,
                newItem: PrivatBankRate
            ): Boolean {
                return oldItem.name == newItem.name && oldItem.buy == newItem.buy
            }

            override fun areContentsTheSame(
                oldItem: PrivatBankRate,
                newItem: PrivatBankRate
            ): Boolean {
                return oldItem.name == newItem.name && oldItem.sale == newItem.sale
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PrivatViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_privatbank_list_item, parent, false)
        return PrivatViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: PrivatViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) holder.bind(item, listener)
    }

    class PrivatViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private var pbBaseCurrency: TextView? = view.findViewById(R.id.namePB)
        private var pbBuy: TextView? = view.findViewById(R.id.buyPB)
        private var pbSale: TextView? = view.findViewById(R.id.salePB)

        fun bind(privat: PrivatBankRate, listener: Listener) {
            pbBaseCurrency?.text = privat.name
            pbBuy?.text = privat.buy.toString()
            pbSale?.text = privat.sale.toString()

            itemView.setOnClickListener { listener.onClick(privat)
            }
        }
    }

    interface Listener {
        fun onClick(privat: PrivatBankRate)
    }
}
