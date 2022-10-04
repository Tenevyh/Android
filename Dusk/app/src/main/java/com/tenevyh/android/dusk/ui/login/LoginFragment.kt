package com.tenevyh.android.dusk.ui.login

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.ktx.Firebase

class LoginFragment : Fragment() {

    private var chatAuthStateListener: ChatAuthStateListener? = null
    private val providers = arrayListOf(
        AuthUI.IdpConfig.EmailBuilder().build(),
        AuthUI.IdpConfig.PhoneBuilder().build(),
        AuthUI.IdpConfig.GoogleBuilder().build(),
    )
    private val signLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract()){
        res -> this.onSignInResult(res)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if(context is ChatAuthStateListener) {
            chatAuthStateListener = context
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        doLogin()
    }

    private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult){}

    private fun doLogin() {
        val signIntent = AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            //.setLogo()
            .setAlwaysShowSignInMethodScreen(true)
            .setIsSmartLockEnabled(false)
            .setTosAndPrivacyPolicyUrls(
                "https://in.bpbonline.com/policies/terms-of-service",
                "https://in.bpbonline.com/pages/privacy-policy"
            ).build()
        signLauncher.launch(signIntent)

    }
}