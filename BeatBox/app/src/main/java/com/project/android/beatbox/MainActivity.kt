package com.project.android.beatbox

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.project.android.beatbox.R
import com.project.android.beatbox.databinding.ActivityMainBinding
import com.project.android.beatbox.databinding.ListItemSoundBinding


class MainActivity : AppCompatActivity(), EditSound {

    private lateinit var beatBoxViewModel: BeatBoxViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val beatBoxFactoryModel = BeatBoxFactoryModel(assets)
        beatBoxViewModel = ViewModelProvider(this, beatBoxFactoryModel).get(BeatBoxViewModel::class.java)

        val binding : ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = SoundAdapter(beatBoxViewModel.beatBox.sounds)
        }
    }

    private inner class SoundHolder(private val binding: ListItemSoundBinding):
        RecyclerView.ViewHolder(binding.root) {

            init {
                binding.viewModel = SoundViewModel(beatBoxViewModel.beatBox)
            }

        fun bind(sound: Sound) {
            binding.apply {
                viewModel?.sound = sound
                executePendingBindings()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private inner class SoundAdapter(private val sounds: List<Sound>): RecyclerView.Adapter<SoundHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SoundHolder {
            val binding = DataBindingUtil.inflate<ListItemSoundBinding>(
                layoutInflater,
                R.layout.list_item_sound,
                parent,
                false
            )
            return SoundHolder(binding)
        }

        override fun onBindViewHolder(holder: SoundHolder, position: Int) {
            val sound = sounds[position]
            holder.bind(sound)
        }

        override fun getItemCount() = sounds.size
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.sound_level, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        beatBoxViewModel.beatBox.speed = 1.0f
        val fragment = SoundSpeed().show(supportFragmentManager, "SoundSpeed")
        return true
    }

    override fun editSpeed(speed: Float) {
        beatBoxViewModel.beatBox.speed = speed
    }
}