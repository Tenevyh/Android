package com.tenevyh.android.chatno


import androidx.recyclerview.widget.RecyclerView

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.tenevyh.android.chatno.databinding.ActivityMainBinding


class Database {

    companion object {
        val instance = Database()
    }


    fun messageListener(dRef: DatabaseReference, adapter: UserAdapter,
                        binding: ActivityMainBinding, rcView: RecyclerView){
        dRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = ArrayList<User>()
                for(s in snapshot.children){
                    val user = s.getValue(User::class.java)
                    if (user != null) list.add(user)
                }
                adapter.submitList(list)
                if(list.size > 0) {
                    rcView.smoothScrollToPosition(list.size - 1)
            }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}