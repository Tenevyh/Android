package com.tenevyh.android.dusk.ui.utils

import com.google.firebase.auth.FirebaseUser
import com.google.firebase.ktx.Firebase
import com.tenevyh.android.dusk.ui.repository.ChatUser

fun mapFromFirebaseUser(user: FirebaseUser): ChatUser {
    return ChatUser()
}