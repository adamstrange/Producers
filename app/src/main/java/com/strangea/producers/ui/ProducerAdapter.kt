package com.strangea.producers.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.strangea.producers.R
import com.strangea.producers.database.Producer
import kotlinx.android.synthetic.main.item_list_content.view.*

class ProducerAdapter (
    diffCallback: DiffUtil.ItemCallback<Producer>
    ) : PagedListAdapter<Producer, ProducerAdapter.ViewHolder>(diffCallback) {

    private val onClickListener: View.OnClickListener

    init {
        onClickListener = View.OnClickListener { v ->
            val item = v.tag as Producer
            val intent = Intent(v.context, ItemDetailActivity::class.java).apply {
                putExtra(ItemDetailActivity.ARG_ITEM, item)
            }
            v.context.startActivity(intent)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list_content, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let {
            holder.idView.text = it.name
            holder.contentView.text = if(it.shortDescription.isNullOrBlank()) {it.description} else {it.shortDescription}

            with(holder.itemView) {
                tag = it
                setOnClickListener(onClickListener)
            }
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val idView: TextView = view.id_text
        val contentView: TextView = view.content
    }
}
