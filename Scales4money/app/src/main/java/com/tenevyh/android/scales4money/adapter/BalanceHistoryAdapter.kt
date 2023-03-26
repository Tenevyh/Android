package com.tenevyh.android.scales4money.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.tenevyh.android.scales4money.Balance
import com.tenevyh.android.scales4money.R
import com.tenevyh.android.scales4money.databinding.HistoryItemBinding
import java.text.SimpleDateFormat

class BalanceHistoryAdapter(val balanceSheets: List<Balance>)
    : ListAdapter<Balance, BalanceHistoryAdapter.Holder>(
    Comparator()){


    class Holder(view: View): RecyclerView.ViewHolder(view) {
        val binding = HistoryItemBinding.bind(view)
        private val dateFormat = SimpleDateFormat("d MMM yyyy")

        fun bind(item: Balance) = with (binding){
            itemBalance.text = item.number
            tvDate.text = dateFormat.format(item.date)
            Picasso.get().load(item.img).into(upOrDownIV)
        }
    }

    class Comparator: DiffUtil.ItemCallback<Balance>(){
        override fun areItemsTheSame(oldItem: Balance, newItem: Balance): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: Balance, newItem: Balance): Boolean {
            return oldItem==newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.history_item, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val balance = balanceSheets[position]
        holder.bind(balance)
    }

    override fun getItemCount() = balanceSheets.size
}