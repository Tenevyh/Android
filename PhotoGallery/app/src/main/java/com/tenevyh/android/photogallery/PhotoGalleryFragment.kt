package com.tenevyh.android.photogallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.tenevyh.android.photogallery.FlickrApi.FlickrApi
import com.tenevyh.android.photogallery.databinding.FragmentPhotoGalleryBinding
import retrofit2.Retrofit
import retrofit2.create

class PhotoGalleryFragment: Fragment() {
    private var _binding: FragmentPhotoGalleryBinding? = null
    private val binding
        get() = checkNotNull (_binding) {
            "Cannot access binding because it is null. Is the view visible?"
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPhotoGalleryBinding.inflate(inflater, container, false)
        binding.photoGrid.layoutManager = GridLayoutManager(context, 3)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("http://www.flickr.com/")
            .build()

        val flickrApi: FlickrApi = retrofit.create<FlickrApi>()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}