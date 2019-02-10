package com.strangea.producers.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

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
