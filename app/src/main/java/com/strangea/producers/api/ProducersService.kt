package com.strangea.producers.api

import com.strangea.producers.entities.Producer
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ProducersService{

    @GET("producers")
    fun getProducers(@Query("page")page :Int, @Query("per_page_limit")  perPageLimit : Int):Call<Producer>
}