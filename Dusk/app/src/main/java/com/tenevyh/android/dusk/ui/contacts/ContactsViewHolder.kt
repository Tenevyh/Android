package com.tenevyh.android.dusk.ui.contacts

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.squareup.picasso.Picasso
import com.tenevyh.android.dusk.R
import com.tenevyh.android.dusk.ui.repository.ChatUser

class ContactsViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    private val name: TextView = itemView.findViewById(R.id.contactTextView)
    private val pic: ImageView = itemView.findViewById(R.id.ivUserImage)

    fun bind(user: ChatUser) {
        if (FirebaseAuth.getInstance().currentUser == null) {
        } else {
            name.text = user.displayName
            user.photoUrl?.let { _photoUrl ->
                if (_photoUrl.isNotEmpty()) {
                    Picasso.get().load(_photoUrl)
                        .placeholder(R.drawable.ic_anon_user_48dp)
                        .into(pic)
                }
            }
        }
    }
}