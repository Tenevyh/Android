package com.bignerdranch.android.beatbox

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.TextView
import androidx.fragment.app.DialogFragment

class SoundLevel: DialogFragment(), SeekBar.OnSeekBarChangeListener{

    private lateinit var level: SeekBar
    private lateinit var levelText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.seek_bar, container, false)
        level = view.findViewById(R.id.seek)
        level.setOnSeekBarChangeListener(this)
        levelText = view.findViewById(R.id.value)
        levelText.setText(0)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
        TODO("Not yet implemented")
    }

    override fun onStartTrackingTouch(p0: SeekBar?) {
        TODO("Not yet implemented")
    }

    override fun onStopTrackingTouch(p0: SeekBar?) {
        levelText.setText(level.progress)
    }
}