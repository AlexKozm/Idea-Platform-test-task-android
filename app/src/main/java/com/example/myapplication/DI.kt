package com.example.myapplication

import androidx.room.Room
import com.example.data.products.data.ProductRepo
import com.example.data.products.db.AppDatabase
import com.example.feature.products.ProductsListViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val mainModule = module {
    single<AppDatabase> {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java, "database-name"
        )
            .createFromAsset("database/data.db")
            .build()
    }
    singleOf(::ProductRepo)
    viewModelOf(::ProductsListViewModel)
}