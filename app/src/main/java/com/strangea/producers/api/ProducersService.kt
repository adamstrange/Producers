package com.strangea.producers.api

import com.strangea.producers.entities.Producer
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET

interface ProducersService{

    @GET("producers")
    fun getProducers():Single<Response<Producer>>
}