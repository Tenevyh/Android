package com.bignerdranch.android.criminalintent

import android.graphics.Bitmap
import android.graphics.BitmapFactory

fun getScaledBitmap(path: String, destWidth: Int, destHeight: Int): Bitmap {
    // Чтение размеров изображения на диске
    var options = BitmapFactory.Options()
    options.inJustDecodeBounds = true
    BitmapFactory.decodeFile(path, options)

    val scrWidth = options.outWidth.toFloat()
    val scrHeight = options.outHeight.toFloat()

    //На сколько нужно уменьшить?
    val inSampleSize = 1
    if (scrHeight > destHeight || scrWidth > destWidth)

}