package com.tenevyh.android.composephoto

import android.content.Context
import android.graphics.SurfaceTexture
import android.view.Surface
import android.view.TextureView
import android.widget.FrameLayout
import androidx.camera.core.Preview
import androidx.camera.core.SurfaceRequest
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class PreviewView(context: Context) : FrameLayout(context) {
    val viewFinder = PreviewViewFinder(context)
    private var surfaceInitialized = false

    init {
        addView(viewFinder)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val previewAspectRatio = 3f / 4f
        val previewWidth = MeasureSpec.getSize(widthMeasureSpec)
        val previewHeight = (previewWidth / previewAspectRatio).toInt()

        viewFinder.layoutParams = LayoutParams(previewWidth, previewHeight)


        super.onMeasure(
            MeasureSpec.makeMeasureSpec(previewWidth, MeasureSpec.EXACTLY),
            MeasureSpec.makeMeasureSpec(previewHeight, MeasureSpec.EXACTLY)
        )
    }

    fun setPreview(preview: Preview) {
        preview.setSurfaceProvider(viewFinder)
        surfaceInitialized = true
    }

    fun isSurfaceInitialized(): Boolean {
        return surfaceInitialized
    }
}

class PreviewViewFinder(context: Context) : TextureView(context), Preview.SurfaceProvider {
    override fun onSurfaceRequested(request: SurfaceRequest) {
        this.surfaceTextureListener = object : SurfaceTextureListener {
            override fun onSurfaceTextureAvailable(surface: SurfaceTexture, width: Int, height: Int) {
                val surface = Surface(surface)
                request.provideSurface(surface, cameraExecutor, { surface.release() })
            }

            override fun onSurfaceTextureSizeChanged(surface: SurfaceTexture, width: Int, height: Int) {}
            override fun onSurfaceTextureUpdated(surface: SurfaceTexture) {}
            override fun onSurfaceTextureDestroyed(surface: SurfaceTexture): Boolean = true
        }
    }

    private val cameraExecutor: ExecutorService = Executors.newSingleThreadExecutor()
}
