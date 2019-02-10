package com.strangea.producers

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.strangea.producers.entities.Producer
import kotlinx.android.synthetic.main.activity_item_list.*
import kotlinx.android.synthetic.main.item_list.*
import retrofit2.Response
import androidx.recyclerview.widget.DividerItemDecoration



class ItemListActivity : AppCompatActivity(), LifecycleOwner {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_list)

        setSupportActionBar(toolbar)
        toolbar.title = title

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        recyclerView.addItemDecoration(DividerItemDecoration(recyclerView.context, DividerItemDecoration.VERTICAL))
        val countryAdapter = ProducerAdapter(ProducerCallback())
        recyclerView.adapter = countryAdapter

        val producerObserver = Observer<Response<Producer>> { response ->
            if(response.isSuccessful) {
                countryAdapter.submitList(response.body()?.producerResponse)
            } else {
                Snackbar.make(recyclerView, response.message(), Snackbar.LENGTH_LONG).show()
            }
        }

        val viewModel = ViewModelProviders.of(this).get(ProducerViewModel::class.java)
        viewModel.producerLiveData.observe(this, producerObserver)
        viewModel.loadProducers()
    }

}
