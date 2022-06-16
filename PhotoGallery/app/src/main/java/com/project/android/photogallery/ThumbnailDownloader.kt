package com.project.android.photogallery

import android.os.HandlerThread
import android.util.Log
import java.util.logging.Handler

private const val TAG = "ThumbnailDownloader"

class ThumbnailDownloader<in T>: HandlerThread(TAG) {
    private var hasQuit = false

    override fun quit(): Boolean {
        hasQuit = true
        return super.quit()
    }

    fun queueThumbnail(target: T, url: String) {
        Log.i(TAG, "Got a URL: $url")
    }
}