package com.project.android.beatbox

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.project.android.beatbox.R

class SoundSpeed: DialogFragment(), SeekBar.OnSeekBarChangeListener{

    private lateinit var level: SeekBar
    private lateinit var levelText: TextView
    private lateinit var editSound: EditSound

    override fun onAttach(context: Context) {
        super.onAttach(context)
        editSound = activity as EditSound
    }

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
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
        levelText.text = "Speed : $p1"
        editSound.editSpeed(p1.toFloat())
    }


    override fun onStartTrackingTouch(p0: SeekBar?) {

    }

    override fun onStopTrackingTouch(p0: SeekBar?) {

    }
}