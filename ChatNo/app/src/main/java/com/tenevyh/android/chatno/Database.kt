package com.tenevyh.android.chatno

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.tenevyh.android.chatno.databinding.ActivityMainBinding
import com.tenevyh.android.chatno.databinding.ActivitySignInBinding

class Database {

    companion object {
        val instance = Database()
    }


    private lateinit var listUser: ArrayList<User>
    private lateinit var lastIdMessage: String

    fun getLastIdMessage(): String{
        return lastIdMessage
    }

    fun getListUser(): ArrayList<User>{
        return listUser
    }


    fun messageListener(dRef: DatabaseReference, adapter: UserAdapter,
                        binding: ActivityMainBinding, rcView: RecyclerView){
        dRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot): Unit  {
                val list = ArrayList<User>()
                for(s in snapshot.children){
                    val user = s.getValue(com.tenevyh.android.chatno.User::class.java)
                    if (user != null) list.add(user)
                }
                listUser = list
                Log.d("MyLog", "list: $list")
                adapter.submitList(list)
                if(list.size > 0) {
                    rcView.smoothScrollToPosition(list.size - 1)
                    lastIdMessage = snapshot.children.elementAt(list.size - 1)?.key.toString()
                    Log.d("MyLog", "list: $lastIdMessage")
            }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}