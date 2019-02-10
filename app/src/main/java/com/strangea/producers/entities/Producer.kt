package com.strangea.producers.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Producer {

    @SerializedName("response")
    @Expose
    var producerResponse: List<ProducerResponse>? = null
    @SerializedName("count")
    @Expose
    var count: Int? = null
    @SerializedName("pagination")
    @Expose
    var pagination: Pagination? = null

}
