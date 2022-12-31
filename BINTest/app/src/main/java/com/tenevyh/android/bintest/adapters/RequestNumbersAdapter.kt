package com.tenevyh.android.bintest.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.tenevyh.android.bintest.R
import com.tenevyh.android.bintest.RequestNumber
import com.tenevyh.android.bintest.databinding.HistoryItemBinding

class RequestNumbersAdapter(val cardNumbers: List<RequestNumber>): ListAdapter<RequestNumber,
        RequestNumbersAdapter.Holder>(Comparator()) {

    class Holder(view: View) : RecyclerView.ViewHolder(view){
        val binding = HistoryItemBinding.bind(view)


        fun bind(item: RequestNumber) = with (binding){
            itemNumberCardTV.text = item.number
            itemDateTV.text = item.date.toString()
        }
    }

    class Comparator : DiffUtil.ItemCallback<RequestNumber>(){
        override fun areItemsTheSame(oldItem: RequestNumber, newItem: RequestNumber): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: RequestNumber, newItem: RequestNumber): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.history_item,
            parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val number = cardNumbers[position]
        holder.bind(number)
    }

    override fun getItemCount() = cardNumbers.size
}