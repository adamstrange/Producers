package com.strangea.producers.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.recyclerview.widget.DividerItemDecoration
import com.strangea.producers.ProducerViewModel
import com.strangea.producers.R
import com.strangea.producers.database.Producer
import kotlinx.android.synthetic.main.activity_item_list.*

class ItemListActivity : AppCompatActivity(), LifecycleOwner {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_list)

        setSupportActionBar(toolbar)
        toolbar.title = title

        recyclerView.addItemDecoration(DividerItemDecoration(recyclerView.context, DividerItemDecoration.VERTICAL))
        val adapter = ProducerAdapter(ProducerCallback())
        recyclerView.adapter = adapter

        observeFullList()

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            var oldQuery:String? = null
            override fun onQueryTextSubmit(query: String?): Boolean {
                search(query!!)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    if(it.isEmpty()){
                        observeFullList()
                    }
                }
                return true
            }

            fun search(query: String){
                if(oldQuery != query) {
                    oldQuery = query
                    val viewModel = ViewModelProviders.of(this@ItemListActivity).get(ProducerViewModel::class.java)
                    viewModel.search(query)
                    if (viewModel.pagedList.hasActiveObservers()) {
                        viewModel.pagedList.removeObservers(this@ItemListActivity)
                        viewModel.searchList?.observe(this@ItemListActivity, Observer<PagedList<Producer>> {
                            (recyclerView.adapter as ProducerAdapter).submitList(it)
                        })
                    }
                }
            }
        })
    }

    private fun observeFullList(){
        val viewModel = ViewModelProviders.of(this).get(ProducerViewModel::class.java)
        if(!viewModel.pagedList.hasActiveObservers()) {
            val producerObserver = Observer<PagedList<Producer>> { response ->
                (recyclerView.adapter as ProducerAdapter).submitList(response)
            }
            viewModel.pagedList.observe(this, producerObserver)
            viewModel.searchList?.let {
                if(it.hasActiveObservers()){
                    it.removeObservers(this@ItemListActivity)
                }
            }
        }


    }

}
