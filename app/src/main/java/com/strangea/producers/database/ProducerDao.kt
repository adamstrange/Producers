package com.strangea.producers.database

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ProducerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(producers: List<Producer>)

    @Query("SELECT * FROM producer_table ORDER BY currentPage ASC")
    fun producersByPage(): DataSource.Factory<Int, Producer>
}
