package com.strangea.producers

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.strangea.producers.datasource.ProducerDataSource
import com.strangea.producers.entities.ProducerResponse
import java.util.concurrent.Executors


class ProducerViewModel(application: Application) : AndroidViewModel(application){

    private val dataSourceFactory = ProducerDataSourceFactory()
    val dataSource = dataSourceFactory.create()
    val list: LiveData<PagedList<ProducerResponse>> =
        LivePagedListBuilder(dataSourceFactory, 20)
            .setFetchExecutor(Executors.newSingleThreadExecutor())
            .build()

    class ProducerDataSourceFactory :
        DataSource.Factory<Int, ProducerResponse>() {
        private val sourceLiveData = MutableLiveData<ProducerDataSource>()
        override fun create(): DataSource<Int, ProducerResponse> {
            val source = ProducerDataSource()
            sourceLiveData.postValue(source)
            return source
        }
    }
}
