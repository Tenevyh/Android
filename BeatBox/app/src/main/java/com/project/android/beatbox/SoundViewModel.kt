package com.project.android.beatbox

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.lifecycle.ViewModel

class SoundViewModel(private val beatBox: BeatBox): BaseObservable(){
    fun onButtonClicked() {
        sound?.let {
            beatBox.play(it)
        }
    }

    var sound: Sound? = null
    set(sound) {
        field = sound
        notifyChange()
    }

    @get:Bindable
    val title: String?
    get()= sound?.name
}