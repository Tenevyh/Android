package com.bignerdranch.android.geomain

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.ViewModelProviders.of

class ChooseHero: DialogFragment () {


    private lateinit var layla : Button
    private lateinit var zask : Button
    private lateinit var vanvan : Button
    private lateinit var valir : Button
    private lateinit var back : Button
    private lateinit var mCallBack : SelectedHero

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        mCallBack = activity as SelectedHero
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.choose_hero_fragment,container,false)
        layla = view.findViewById(R.id.layla) as Button
        zask = view.findViewById(R.id.zask) as Button
        vanvan = view.findViewById(R.id.vanvan) as Button
        valir = view.findViewById(R.id.valir) as Button
        back = view.findViewById(R.id.back) as Button
        return view
    }

    override fun onStart() {
        super.onStart()


        layla.setOnClickListener{
            mCallBack.clickHero(0)
            onDestroyView()
        }

        zask.setOnClickListener{
            mCallBack.clickHero(1)
            onDestroyView()
        }

        vanvan.setOnClickListener{
            mCallBack.clickHero(2)
            onDestroyView()
        }

        valir.setOnClickListener{
            mCallBack.clickHero(3)
            onDestroyView()
        }

        back.setOnClickListener{
            onDestroyView()
        }
    }

    override fun getTheme(): Int {
        return R.style.Theme_GeoQuiz_Fullscreen
    }
}