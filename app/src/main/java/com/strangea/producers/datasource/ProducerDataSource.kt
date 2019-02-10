package com.strangea.producers.datasource

import androidx.paging.ItemKeyedDataSource
import com.strangea.producers.api.ApiManager
import com.strangea.producers.entities.Pagination
import com.strangea.producers.entities.ProducerResponse

class ProducerDataSource : ItemKeyedDataSource<Int, ProducerResponse>() {

    private var pagination: Pagination? = null

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<ProducerResponse>) {}

    override fun getKey(item: ProducerResponse) = pagination?.next ?: 1

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<ProducerResponse>) {
        val items = fetchItems(1, params.requestedLoadSize)
        callback.onResult(items)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<ProducerResponse>) {
        val items = fetchItems(params.key, params.requestedLoadSize)
        callback.onResult(items)
    }

    private fun fetchItems(page:Int, size:Int):List<ProducerResponse>{
        pagination?.let {
            if(it.next == null){
                return emptyList()
            }
        }
        val response = ApiManager.getApiManager().service.getProducers(page, size).execute()
        if(response.isSuccessful){
            response.body()?.let {
                pagination = it.pagination
                 it.producerResponse?.let {list->
                     return list
                 }
            }
        }
        return emptyList()
    }
}