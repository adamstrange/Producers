package com.strangea.producers.database

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "producer_table")
data class Producer(
    var currentPage: Int? = null,
    var nextPage: Int? = null,
    var image: String? = null,
    @PrimaryKey
    var id: Int? = null,
    var name: String? = null,
    var shortDescription: String? = null,
    var description: String? = null,
    var location: String? = null) : Parcelable
