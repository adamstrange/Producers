package com.strangea.producers.entities

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Image(

    @SerializedName("path")
    @Expose
    var path: String? = null,
    @SerializedName("position")
    @Expose
    var position: Int? = null

) : Parcelable
