package com.dicoding.literalinkapps

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Book(
    val title: String,
    val author:String,
    val genre: String,
    val photo: Int,
    val publisher :String,
    val synopsis : String,
    val language : String,
    val year : Int,
    val page : Int

): Parcelable