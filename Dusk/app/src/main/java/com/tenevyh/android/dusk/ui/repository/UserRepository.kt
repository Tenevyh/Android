package com.tenevyh.android.dusk.ui.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.tenevyh.android.dusk.ui.utils.FetchChatUserListener
import com.tenevyh.android.dusk.ui.utils.mapFromFirebaseUser

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

    fun getCurrentUserByUid(firebaseUser: FirebaseUser, listener: FetchChatUserListener) {
        val database = Firebase.database
        val userDbRef = database.getReference("users_2")
        userDbRef.child(firebaseUser.uid).addListenerForSingleValueEvent(
            object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val user = snapshot.getValue<ChatUser>()
                        ?: mapFromFirebaseUser(FirebaseAuth.getInstance().currentUser!!)
                    listener.onFetchUser(user)
                }

                override fun onCancelled(error: DatabaseError) {
                    val user = mapFromFirebaseUser(FirebaseAuth.getInstance().currentUser!!)
                    listener.onFetchUser(user)
                }
            }
        )
    }
}