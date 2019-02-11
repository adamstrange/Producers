package com.strangea.producers.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.recyclerview.widget.DividerItemDecoration
import com.strangea.producers.ProducerViewModel
import com.strangea.producers.R
import com.strangea.producers.database.Producer
import kotlinx.android.synthetic.main.activity_item_list.*
import kotlinx.android.synthetic.main.item_list.*


class ItemListActivity : AppCompatActivity(), LifecycleOwner {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_list)

        setSupportActionBar(toolbar)
        toolbar.title = title

        recyclerView.addItemDecoration(DividerItemDecoration(recyclerView.context, DividerItemDecoration.VERTICAL))
        val adapter = ProducerAdapter(ProducerCallback())
        recyclerView.adapter = adapter

        val producerObserver = Observer<PagedList<Producer>> { response ->
            adapter.submitList(response)
        }

        val viewModel = ViewModelProviders.of(this).get(ProducerViewModel::class.java)
        viewModel.list.observe(this, producerObserver)
    }

}