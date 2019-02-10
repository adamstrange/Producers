package com.strangea.producers.entities

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProducerResponse(

    @SerializedName("id")
    @Expose
    var id: Int? = null,
    @SerializedName("name")
    @Expose
    var name: String? = null,
    @SerializedName("permalink")
    @Expose
    var permalink: String? = null,
    @SerializedName("created_at")
    @Expose
    var createdAt: String? = null,
    @SerializedName("updated_at")
    @Expose
    var updatedAt: String? = null,
    @SerializedName("images")
    @Expose
    var images: List<Image>? = null,
    @SerializedName("short_description")
    @Expose
    var shortDescription: String? = null,
    @SerializedName("description")
    @Expose
    var description: String? = null,
    @SerializedName("location")
    @Expose
    var location: String? = null,
    @SerializedName("via_wholesaler")
    @Expose
    var viaWholesaler: Boolean? = null,
    @SerializedName("wholesaler_name")
    @Expose
    var wholesalerName: String? = null) : Parcelable
