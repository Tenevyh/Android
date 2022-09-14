package com.tenevyh.android.chatno

import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso
import com.tenevyh.android.chatno.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var adapter: UserAdapter


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
        if (auth.currentUser?.photoUrl != null){
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