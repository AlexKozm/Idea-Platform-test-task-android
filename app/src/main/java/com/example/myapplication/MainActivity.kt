package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.room.Room
import com.example.data.products.db.AppDatabase
import com.example.data.products.data.ProductRepo
import com.example.feature.products.ProductsListScreen
import com.example.feature.products.ProductsListViewModel
import com.example.theme.MyApplicationTheme
import com.example.theme.SystemUIColor
import org.koin.androidx.compose.koinViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val vm: ProductsListViewModel by viewModel()
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
                                    text = stringResource(R.string.list_of_products),
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
                        onSearchTextChange = { vm.updateSearchFilter(it) },
                        onItemDelete = { vm.deleteProduct(it) },
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