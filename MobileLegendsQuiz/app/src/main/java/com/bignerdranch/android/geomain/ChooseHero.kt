package com.bignerdranch.android.geomain

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

            onDestroyView()
        }

        zask.setOnClickListener{

            onDestroyView()
        }

        vanvan.setOnClickListener{

            onDestroyView()
        }

        valir.setOnClickListener{

            onDestroyView()
        }

        back.setOnClickListener{
            onDestroyView()
        }
    }
}