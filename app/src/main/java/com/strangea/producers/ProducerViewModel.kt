package com.strangea.producers

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.strangea.producers.api.ApiManager
import com.strangea.producers.api.ProducerResponse
import com.strangea.producers.api.ProducersService
import com.strangea.producers.database.Producer
import com.strangea.producers.database.ProducerDao
import com.strangea.producers.database.ProducerRoomDatabase
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Executors


class ProducerViewModel(application: Application) : AndroidViewModel(application){

    var list: LiveData<PagedList<Producer>>

    init {
        val dao = ProducerRoomDatabase.getDatabase(getApplication())!!.producerDao()
        val dataSource : DataSource.Factory<Int, Producer> = dao.producersByPage()

        list = LivePagedListBuilder(dataSource, 20)
                .setBoundaryCallback(ProducerBoundaryCallback(
                    ApiManager.getApiManager().service,
                    dao))
                .setFetchExecutor(Executors.newSingleThreadExecutor())
                .build()
    }

    class ProducerBoundaryCallback(
        private val service: ProducersService,
        private val cache: ProducerDao
    ) : PagedList.BoundaryCallback<Producer>() {

        override fun onZeroItemsLoaded() {
            requestAndAppendData()
        }

        override fun onItemAtEndLoaded(itemAtEnd: Producer) {
            itemAtEnd.nextPage?.let {
                requestAndAppendData(it)
            }
        }

        private fun requestAndAppendData(page:Int = 1){
            service.getProducers(page, 20)
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .subscribe(object : SingleObserver<ProducerResponse> {
                    override fun onSubscribe(d: Disposable) {}

                    override fun onSuccess(producerResponse: ProducerResponse) {
                        producerResponse.producerResponseItems?.let { list->
                            cache.insert(list.map{ item -> convert(producerResponse.pagination!!, item)})
                        }
                    }

                    override fun onError(e: Throwable) {}
                })
        }

        private fun convert(pagination: ProducerResponse.Pagination, responseItem: ProducerResponse.ProducerResponseItem): Producer {
            return Producer(pagination.current, pagination.next,
                responseItem.images?.get(0)?.path ?: "", //TODO use placeholder instead of empty string
                responseItem.id,
                responseItem.name,
                responseItem.shortDescription,
                responseItem.description,
                responseItem.location
            )
        }
    }
}
