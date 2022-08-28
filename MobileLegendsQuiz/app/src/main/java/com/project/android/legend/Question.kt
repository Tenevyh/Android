package com.project.android.legend

data class Question (val textResId: String, val answer: Boolean, var completed: Boolean = false, var cheat : Boolean= false){
}