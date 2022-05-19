package com.project.android.Quiz

import androidx.annotation.StringRes

data class Question (val textResId: String, val answer: Boolean, var completed: Boolean = false, var cheat : Boolean= false){
}