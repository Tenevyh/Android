package com.tenevyh.android.weatherforyou.Dialog

import android.app.AlertDialog
import android.content.Context

object DialogManager {
    fun locationSettingDialog(context: Context, listener: Listener){
        val builder = AlertDialog.Builder(context)
        val dialog = builder.create()
        dialog.setTitle("Enabled location?")
        dialog.setMessage("Location disabled, do you want enable location?")
        dialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK"){
            _,_, ->
            listener.onClick()
            dialog.dismiss()
        }
        dialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel"){
                _,_, ->
            dialog.dismiss()
        }
        dialog.show()
    }

    interface Listener{
        fun onClick()
    }
}