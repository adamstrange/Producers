package com.strangea.producers.ui

import androidx.recyclerview.widget.DiffUtil
import com.strangea.producers.database.Producer

class ProducerCallback  : DiffUtil.ItemCallback<Producer>() {
    override fun areItemsTheSame(oldItem: Producer, newItem: Producer): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Producer, newItem: Producer): Boolean {
        return oldItem == newItem
    }

}