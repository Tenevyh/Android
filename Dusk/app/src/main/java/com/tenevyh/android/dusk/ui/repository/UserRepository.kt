package com.tenevyh.android.dusk.ui.repository

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage

private const val CHAT_USER_TABLE = "users"

class UserRepository{

    private val rootRef = FirebaseStorage.getInstance().reference
    private val feedbackRef = rootRef.child("user_feedback")

    fun createOrUpdateUser(user: ChatUser) {
        val database = Firebase.database
        val userRef = database.getReference("user_2")
        val userNodeRef = userRef.child(user.uid)
        userNodeRef.setValue(user)
    }
}