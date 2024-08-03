package com.example.myapplication

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.ViewCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.room.Room
import com.example.myapplication.configs.AppDatabase
import com.example.myapplication.products.data.ProductRepo
import com.example.myapplication.screens.productsList.ProductsListScreen
import com.example.myapplication.screens.productsList.ProductsListViewModel
import com.example.myapplication.ui.theme.BackgroundColor
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.ui.theme.SystemUIColor

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        )
            .createFromAsset("database/data.db")
            .build()
        val vm = ProductsListViewModel(
            ProductRepo(db)
        )
        setContent {
            MyApplicationTheme {
                SideEffect {
                    window.navigationBarColor = SystemUIColor.toArgb()
                }
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            modifier = Modifier.fillMaxWidth(),
                            title = {
                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = "Список товаров",
                                    textAlign = TextAlign.Center
                                )
                            },
                            colors = TopAppBarDefaults.topAppBarColors().copy(
                                containerColor = SystemUIColor
                            )
                        )
                    }
                ) { innerPadding ->
                    val searchFilter by vm.searchFilter.collectAsStateWithLifecycle()
                    ProductsListScreen(
                        modifier = Modifier.padding(innerPadding),
                        searchText = searchFilter,
                        onSearchTextChange = {vm.updateSearchFilter(it)},
                        onItemDelete = {vm.deleteProduct(it)},
                        onItemAmountChange = { product, amount ->
                            vm.changeAmountOfItems(product, amount)
                        },
                        products = vm.pager.collectAsLazyPagingItems()
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Greeting("Android")
    }
}