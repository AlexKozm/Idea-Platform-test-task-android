package com.example.myapplication.products.data

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import org.jetbrains.annotations.TestOnly

@Dao
internal interface ProductDao {
    @Query("Select * FROM item WHERE lower(name) LIKE :name")
    fun selectAll(name: String = "%"): PagingSource<Int, Product>

    @Delete
    suspend fun delete(vararg products: Product)

    @Update
    suspend fun update(vararg products: Product)

    // From https://developer.android.com/training/data-storage/room/prepopulate
    // Note: In-memory Room databases don't support prepopulating the database
    // using createFromAsset() or createFromFile().
    //
    // So, this method is needed for tests
    @TestOnly
    @Insert
    suspend fun create(vararg products: Product)
}