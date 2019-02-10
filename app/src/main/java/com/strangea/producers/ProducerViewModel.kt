package com.strangea.producers

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.strangea.producers.api.ApiManager
import com.strangea.producers.entities.Producer
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import retrofit2.Response

class ProducerViewModel(application: Application) : AndroidViewModel(application){
    val producerLiveData = MutableLiveData<Response<Producer>>()

    fun loadProducers(){
        ApiManager.getApiManager().service.getProducers()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe(object : SingleObserver<Response<Producer>> {
                override fun onSubscribe(d: Disposable) {}

                override fun onSuccess(response: Response<Producer>) {
                    producerLiveData.postValue(response)
                }

                override fun onError(e: Throwable) {
                    if (e is HttpException) {
                        e.response().errorBody()?.let {
                            producerLiveData.postValue(Response.error(e.code(), it))
                        }
                    }
                }
            })

    }
}
