package com.tenevyh.android.chatno

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.work.*
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
import java.util.concurrent.TimeUnit

private const val WORK = "WORKER"

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var auth: FirebaseAuth
    lateinit var adapter: UserAdapter
    lateinit var lastIdMessage: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth
        setActionBar()

        val database = Database.instance
        val myRef = Firebase.database.getReference("message")



        binding.bSend.setOnClickListener {
            myRef.child(myRef.push().key ?: "bla-bla")
                .setValue(User(auth.currentUser?.displayName, binding.editMessege.text.toString()))
            binding.editMessege.text = null

        }

        initRcView()

        database.messageListener(myRef, adapter, binding, binding.rcView)


        val periodicRequest =
            PeriodicWorkRequestBuilder<Worker>(1, TimeUnit.MINUTES)
                .build()
        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            WORK,
            ExistingPeriodicWorkPolicy.KEEP,
            periodicRequest
        )
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
    companion object{
        fun newIntent(context: Context) : Intent {
            return Intent(context, MainActivity::class.java)
        }
    }
}