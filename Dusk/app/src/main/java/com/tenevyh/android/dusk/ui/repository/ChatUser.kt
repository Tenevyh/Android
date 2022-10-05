package com.tenevyh.android.dusk.ui.repository

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ChatUser(val uid: String ="",
                    val displayName: String ="",
                    val lastSeen: Long = System.currentTimeMillis(),
                    val photoUrl: String? ="") : Parcelable