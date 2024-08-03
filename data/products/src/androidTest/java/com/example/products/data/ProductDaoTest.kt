package com.example.products.data

import android.content.Context
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.testing.TestPager
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.data.products.converters.LocalDateTimeConverter
import com.example.data.products.data.Product
import com.example.data.products.data.ProductDao
import com.example.data.products.data.testData
import com.example.data.products.db.AppDatabase
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test



class ProductDaoTest {
    private lateinit var db: AppDatabase
    private lateinit var productDao: ProductDao

    @Before
    fun createDb() = runTest {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room
            .inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .build()
        productDao = db.getProductDao()
        testData.forEach { productDao.create(it) }
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun checkParserOnFirstItem() = runTest {
        val dbProduct = getProducts(1).first()
        val product = Product(
            1,
            "iPhone 13",
            LocalDateTimeConverter().toDateTime(1633046400000),
            listOf("Телефон", "Новый", "Распродажа"),
            15
        )
        Assert.assertEquals(product, dbProduct)
    }

    @Test
    fun checkParsingOfItemWithEmptyTags() = runTest {
        val dbProduct = getProducts(10)[8]
        val product = Product(
            9,
            "Fitbit Charge 5",
            LocalDateTimeConverter().toDateTime(1633737600000),
            emptyList(),
            27
        )
        Assert.assertEquals(product.tags.size, dbProduct.tags.size)
        Assert.assertEquals(product, dbProduct)
    }


    @Test
    fun readData() = runTest {
        val result = getProducts(100)
        testData.forEachIndexed { index, item ->
            Assert.assertEquals(item, result[index])
        }
    }

    @Test
    fun deleteData() = runTest {
        productDao.delete(testData.first())
        val result = getProducts(100)
        Assert.assertEquals(testData.drop(1), result)
    }

    @Test
    fun editAmount() = runTest {
        val newAmount = 2
        productDao.update(testData.first().copy(amount = newAmount))
        val result = getProducts(100)
        Assert.assertEquals(newAmount, result.first().amount)
    }

    @Test
    fun filter() = runTest {
        val result = getProducts(100, "%play%")
        Assert.assertEquals(1, result.size)
    }

    suspend fun getProducts(num: Int, filter: String = "%"): List<Product> {
        val page = TestPager(PagingConfig(num), productDao.selectAll(filter))
            .refresh() as PagingSource.LoadResult.Page
        return page.data
    }

}