package com.tenevyh.android.dusk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.tenevyh.android.dusk.ui.login.ChatAuthStateListener
import com.tenevyh.android.dusk.ui.login.LoginFragment
import com.tenevyh.android.dusk.ui.main.ChatLandingFragment
import com.tenevyh.android.dusk.ui.utils.replaceFragment

class MainActivity : AppCompatActivity(), ChatAuthStateListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showFragment()
    }

    override fun onAuthStateChanged() {
        if (FirebaseAuth.getInstance().currentUser != null) {
            showFragment()
        }
    }

    private fun showFragment(){
        if (FirebaseAuth.getInstance().currentUser == null){
            replaceFragment(R.id.fragmentContainer, LoginFragment())
        } else {
            replaceFragment(R.id.fragmentContainer, ChatLandingFragment())
        }
    }
    
    override fun onBackPressed() {
        if(supportFragmentManager.backStackEntryCount == 0) {
            finish()
        } else {
            super.onBackPressed()
        }
    }
}