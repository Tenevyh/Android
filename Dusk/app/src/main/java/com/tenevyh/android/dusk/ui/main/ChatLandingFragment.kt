package com.tenevyh.android.dusk.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.tenevyh.android.dusk.R
import com.tenevyh.android.dusk.databinding.FragmentChatHomeBinding


class ChatLandingFragment : Fragment(R.layout.fragment_chat_home){

    private lateinit var binding: FragmentChatHomeBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentChatHomeBinding.inflate(layoutInflater)
        binding.viewPager.adapter = ChatViewPagerAdapter(this)
        TabLayoutMediator(binding.tabLayout, binding.viewPager) {
            tab, position -> tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
    }
}