package com.tenevyh.android.dusk.ui.contacts

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.tenevyh.android.dusk.R
import com.tenevyh.android.dusk.databinding.ItemViewContactsBinding
import com.tenevyh.android.dusk.ui.chat.ChatScreenFragment
import com.tenevyh.android.dusk.ui.repository.ChatUser
import com.tenevyh.android.dusk.ui.repository.UserRepository

import com.tenevyh.android.dusk.ui.utils.mapFromFirebaseUser
import kotlinx.android.synthetic.main.fragment_contacts.*
import kotlinx.coroutines.*


class ContactsFragment : Fragment(R.layout.fragment_contacts){
    private val userQuery = FirebaseDatabase.getInstance().getReference("users")
        .limitToFirst(50)

    private lateinit var adapter: ContactsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareRecyclerView()
    }

    private fun prepareRecyclerView() {
        val options = FirebaseRecyclerOptions.Builder<ChatUser>()
            .setLifecycleOwner(this@ContactsFragment)
            .setQuery(userQuery, ChatUser::class.java)
            .build()

            adapter = ContactsAdapter(options,
                object : ContactsAdapter.OnItemClickListener {
                    override fun onItemClick(chatWith: ChatUser) {
                        FirebaseAuth.getInstance().currentUser?.let { user ->
                            val currentUser = mapFromFirebaseUser(user)
                            ChatScreenFragment.getInstance(
                                currentUser,
                                chatWith
                            ).show(
                                childFragmentManager,
                                ChatScreenFragment::class.java.simpleName
                            )
                        }
                    }
                })

            val layoutManager = LinearLayoutManager(requireContext())
            contactRecyclerView.layoutManager = layoutManager
            contactRecyclerView.setHasFixedSize(true)
            contactRecyclerView.adapter = adapter
            contactRecyclerView.addItemDecoration(
                DividerItemDecoration(
                    contactRecyclerView.context, layoutManager.orientation
                )
            )
    }
}