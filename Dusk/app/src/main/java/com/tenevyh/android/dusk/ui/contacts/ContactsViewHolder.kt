package com.tenevyh.android.dusk.ui.contacts

import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.squareup.picasso.Picasso
import com.tenevyh.android.dusk.R
import com.tenevyh.android.dusk.ui.repository.ChatUser

class ContactsViewHolder(
    itemView: android.view.View) : RecyclerView.ViewHolder(itemView) {

    private val name: TextView = itemView.findViewById(R.id.contactTextView)
    private val pic: ImageView = itemView.findViewById(R.id.ivUserImage)

    fun bind(user: ChatUser) {
        name.text = user.displayName
        user.photoUrl?.let { _photoUrl ->
            if (_photoUrl.isNotEmpty()) {
                Picasso.get().load(_photoUrl)
                    .placeholder(com.firebase.ui.auth.R.drawable.fui_ic_anonymous_white_24dp)
                    .into(pic)
            }
        }
    }

    }