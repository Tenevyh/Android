package com.tenevyh.android.chatno

import android.annotation.SuppressLint
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso
import com.tenevyh.android.chatno.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var auth: FirebaseAuth
    lateinit var adapter: UserAdapter
    private lateinit var lastIdMessage: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth
        setActionBar()

        val database = Firebase.database
        val myRef = database.getReference("message")

        val workRequest = OneTimeWorkRequest.Builder(Worker::class.java).build()
              WorkManager.getInstance().enqueue(workRequest)


        binding.bSend.setOnClickListener {
            myRef.child(myRef.push().key ?: "bla-bla")
                .setValue(User(auth.currentUser?.displayName, binding.editMessege.text.toString()))
            binding.editMessege.text = null

        }

        messageListener(myRef)
        initRcView()
    }

    private fun initRcView() = with(binding){
        adapter = UserAdapter()
        rcView.layoutManager = LinearLayoutManager(this@MainActivity)
        rcView.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.sign_out){
            auth.signOut()
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun messageListener(dRef: DatabaseReference){
        dRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot): Unit = with(binding) {
                val list = ArrayList<User>()
                for(s in snapshot.children){
                    val user = s.getValue(User::class.java)
                    if (user != null) list.add(user)
                }
                adapter.submitList(list)
                if(list.size > 0) {
                    rcView.smoothScrollToPosition(list.size - 1)
                    lastIdMessage = snapshot.children.elementAt(list.size - 1)?.key.toString()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    private fun setActionBar(){
        if (auth.currentUser?.photoUrl == null) {
        } else {
            val actionBar = supportActionBar
            Thread {
                val bitMap = Picasso.get().load(auth.currentUser?.photoUrl).get()
                val drawable = BitmapDrawable(resources, bitMap)
                runOnUiThread {
                    actionBar?.setDisplayHomeAsUpEnabled(true)
                    actionBar?.setHomeAsUpIndicator(drawable)
                    actionBar?.title = auth.currentUser?.displayName
                }
            }.start()
        }
    }
}