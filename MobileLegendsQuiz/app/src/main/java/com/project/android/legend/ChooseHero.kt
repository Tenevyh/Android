package com.project.android.legend

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bignerdranch.android.geomain.R
import com.bignerdranch.android.geomain.databinding.ChooseHeroFragmentBinding

class ChooseHero: DialogFragment () {

    private lateinit var binding: ChooseHeroFragmentBinding
    private lateinit var adapter: HeroAdapter
    private lateinit var viewModel: QuizViewModel


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
        binding = ChooseHeroFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRcView()
    }

    private fun initRcView() = with(binding){
        rcView.layoutManager = LinearLayoutManager(activity)
        adapter = HeroAdapter()
        rcView.adapter = adapter
    }

    override fun onStart() {
        super.onStart()


       /* layla.setOnClickListener{
            mCallBack.clickHero(0)
            onDestroyView()
        }
        */
    }

    override fun getTheme(): Int {
        return R.style.Theme_GeoQuiz_Fullscreen
    }
}