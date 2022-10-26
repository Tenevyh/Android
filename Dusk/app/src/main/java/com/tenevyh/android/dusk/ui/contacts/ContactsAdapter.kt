package com.tenevyh.android.dusk.ui.contacts

import android.view.LayoutInflater
import android.view.ViewGroup
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.tenevyh.android.dusk.R
import com.tenevyh.android.dusk.ui.repository.ChatUser


class ContactsAdapter(private val options: FirebaseRecyclerOptions<ChatUser>,
                      private val onItemClickListener: OnItemClickListener
) : FirebaseRecyclerAdapter<ChatUser, ContactsViewHolder>(options) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder {
        val holder = ContactsViewHolder(LayoutInflater.from(parent.context).inflate(
            R.layout.item_view_contacts, parent, false
        ))

        holder.itemView.setOnClickListener {
            onItemClickListener.onItemClick(
                options.snapshots[holder.absoluteAdapterPosition]
            )
        }

        return holder
    }

    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int, user: ChatUser) {
            holder.bind(user)
    }

    override fun onDataChanged() {
    }

    interface OnItemClickListener{
        fun onItemClick(chatWith: ChatUser)
    }
}