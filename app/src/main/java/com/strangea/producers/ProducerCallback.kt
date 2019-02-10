package com.strangea.producers

import androidx.recyclerview.widget.DiffUtil
import com.strangea.producers.entities.ProducerResponse

class ProducerCallback  : DiffUtil.ItemCallback<ProducerResponse>() {
    override fun areItemsTheSame(oldItem: ProducerResponse, newItem: ProducerResponse): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ProducerResponse, newItem: ProducerResponse): Boolean {
        return oldItem == newItem
    }

}