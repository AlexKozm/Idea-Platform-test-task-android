package com.example.myapplication.products.data

import androidx.paging.PagingSource
import com.example.myapplication.configs.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProductRepo(
    db: AppDatabase
) {
    private val productDao = db.getProductDao()

    fun selectAll(filter: String = ""): PagingSource<Int, Product> =
        productDao.selectAll("%$filter%")

    suspend fun delete(vararg products: Product): Unit = withContext(Dispatchers.IO) {
        productDao.delete(*products)
    }

    suspend fun update(vararg products: Product): Unit = withContext(Dispatchers.IO) {
        productDao.update(*products)
    }
}