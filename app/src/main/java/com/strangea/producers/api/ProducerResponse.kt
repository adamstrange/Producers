package com.strangea.producers.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ProducerResponse {

    @SerializedName("response")
    @Expose
    var producerResponseItems: List<ProducerResponseItem>? = null
    @SerializedName("count")
    @Expose
    var count: Int? = null
    @SerializedName("pagination")
    @Expose
    var pagination: Pagination? = null

    class ProducerResponseItem {
        @SerializedName("id")
        @Expose
        var id: Int? = null
        @SerializedName("name")
        @Expose
        var name: String? = null
        @SerializedName("permalink")
        @Expose
        var permalink: String? = null
        @SerializedName("created_at")
        @Expose
        var createdAt: String? = null
        @SerializedName("updated_at")
        @Expose
        var updatedAt: String? = null
        @SerializedName("images")
        @Expose
        var images: List<Image>? = null
        @SerializedName("short_description")
        @Expose
        var shortDescription: String? = null
        @SerializedName("description")
        @Expose
        var description: String? = null
        @SerializedName("location")
        @Expose
        var location: String? = null
        @SerializedName("via_wholesaler")
        @Expose
        var viaWholesaler: Boolean? = null
        @SerializedName("wholesaler_name")
        @Expose
        var wholesalerName: String? = null
    }

    class Pagination {
        @SerializedName("current")
        @Expose
        var current: Int? = null
        @SerializedName("previous")
        @Expose
        var previous: Any? = null
        @SerializedName("next")
        @Expose
        var next: Int? = null
        @SerializedName("per_page")
        @Expose
        var perPage: Int? = null
        @SerializedName("pages")
        @Expose
        var pages: Int? = null
        @SerializedName("count")
        @Expose
        var count: Int? = null
    }

    class Image {
        @SerializedName("path")
        @Expose
        var path: String? = null
        @SerializedName("position")
        @Expose
        var position: Int? = null
    }


}
