package com.tenevyh.android.composephoto

import android.annotation.SuppressLint
import android.util.Log
import androidx.camera.core.AspectRatio
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import androidx.camera.view.PreviewView
import androidx.camera.core.SurfaceProvider

private const val TAG = "CameraPreview"

@SuppressLint("UnsafeExperimentalUsageError")
@Composable
fun CameraPreview() {
    val cameraExecutor: ExecutorService = Executors.newSingleThreadExecutor()
    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current

    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = { ctx ->
            val cameraProviderFuture = ProcessCameraProvider.getInstance(ctx)

            val previewView = PreviewView(ctx).apply {
                // явно устанавливаем SurfaceProvider
                surfaceProvider = surfaceProvider
            }

            val cameraSelector = CameraSelector.Builder()
                .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                .build()

            try {
                val cameraProvider = cameraProviderFuture.get()
                cameraProvider.unbindAll()

                val preview = Preview.Builder()
                    .setTargetAspectRatio(AspectRatio.RATIO_4_3)
                    .build()

                cameraProvider.bindToLifecycle(
                    lifecycleOwner, cameraSelector, preview
                )

                preview.setSurfaceProvider(previewView.surfaceProvider)
            } catch (e: Exception) {
                Log.e(TAG, "Use case binding failed", e)
            }

            previewView
        }
    )

    lifecycleOwner.lifecycle.addObserver(object : DefaultLifecycleObserver {
        override fun onDestroy(owner: LifecycleOwner) {
            super.onDestroy(owner)
            cameraExecutor.shutdown()
        }
    })
}