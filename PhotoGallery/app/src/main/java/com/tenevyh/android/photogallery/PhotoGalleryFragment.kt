package com.tenevyh.android.photogallery

import androidx.fragment.app.Fragment
import com.tenevyh.android.photogallery.databinding.FragmentPhotoGalleryBinding

class PhotoGalleryFragment: Fragment() {
    private val _binding: FragmentPhotoGalleryBinding? = null
    private val binding
        get() = checkNotNull (_binding) {
            "Cannot access binding because it is null. Is the view visible?"
        }
}