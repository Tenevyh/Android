package com.project.android.legend

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bignerdranch.android.geomain.R
import com.bignerdranch.android.geomain.databinding.ChooseHeroFragmentBinding

class ChooseHero: DialogFragment (), HeroAdapter.Listener {

    private lateinit var binding: ChooseHeroFragmentBinding
    private lateinit var adapter: HeroAdapter
    private val model: QuizViewModel by lazy {
        ViewModelProvider(this)[QuizViewModel::class.java]
    }

    private lateinit var mCallBack: SelectedHero

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        mCallBack = activity as SelectedHero
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ChooseHeroFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRcView()
        model.liveHero.value = model.getHero()
        model.liveHero.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    private fun initRcView() = with(binding) {
        adapter = HeroAdapter(this@ChooseHero)
        rcView.layoutManager = LinearLayoutManager(activity)
        rcView.adapter = adapter
    }

    override fun onStart() {
        super.onStart()
        binding.back.setOnClickListener {
            onDestroyView()
        }
    }

    override fun getTheme(): Int {
        return R.style.Theme_GeoQuiz_Fullscreen
    }

    override fun onClick(item: Hero) {
        mCallBack.clickHero(item.id)
        Log.d("MyLog", "Hero question: ${item.question}")
        onDestroyView()
    }
}