package com.example.goaagara.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class dataNews (
    var id : String,
    var name : String,
    var author : String,
    var title : String,
    var description : String,
    var publishedAt : String,
    var url : String,
    var urlToImage : String
    ) : Parcelable