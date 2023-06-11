package com.tenevyh.android.scales4money.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tenevyh.android.scales4money.R
import com.tenevyh.android.scales4money.Spending
import com.tenevyh.android.scales4money.databinding.SpendingHistoryItemBinding
import java.text.SimpleDateFormat

class SpendingHistoryAdapter (private val spendingSheets: List<Spending>)
    : ListAdapter<Spending, SpendingHistoryAdapter.Holder>(
    Comparator()){

    class Holder(view: View): RecyclerView.ViewHolder(view) {
        private val binding = SpendingHistoryItemBinding.bind(view)
        private val dateFormat = SimpleDateFormat("d MMM yyyy")

        fun bind(item: Spending) = with (binding){
            itemSpending.text = item.number.toString()
            tvDate.text = dateFormat.format(item.date)
        }
    }

    class Comparator: DiffUtil.ItemCallback<Spending>(){
        override fun areItemsTheSame(oldItem: Spending, newItem: Spending): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: Spending, newItem: Spending): Boolean {
            return oldItem==newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.spending_history_item, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val spending = spendingSheets[position]
        holder.bind(spending)
    }

    override fun getItemCount() = spendingSheets.size
}
