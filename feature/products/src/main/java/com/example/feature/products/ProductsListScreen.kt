package com.example.feature.products

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.feature.products.card.ProductCard
import com.example.feature.products.dialogs.DeleteProductDialog
import com.example.feature.products.dialogs.EditProductAmountDialog
import com.example.data.products.data.Product
import com.example.data.products.data.testData
import com.example.theme.BackgroundColor
import kotlinx.coroutines.flow.MutableStateFlow


@Composable
fun ProductsListScreen(
    modifier: Modifier = Modifier,
    searchText: String,
    onSearchTextChange: (String) -> Unit,
    onItemDelete: (Product) -> Unit,
    onItemAmountChange: (Product, amount: Int) -> Unit,
    products: LazyPagingItems<Product>,
) {
    var itemToDelete by remember { mutableStateOf<Product?>(null) }

    itemToDelete?.let { item ->
        DeleteProductDialog(
            onConfirm = {
                onItemDelete(item)
                itemToDelete = null
            },
            onDismiss = { itemToDelete = null }
        )
    }

    var itemToChange by remember { mutableStateOf<Product?>(null) }

    itemToChange?.let { item ->
        EditProductAmountDialog(
            onConfirm = { amount ->
                onItemAmountChange(item, amount)
                itemToChange = null
            },
            onDismiss = { itemToChange = null },
            productToChange = item
        )
    }

    LazyColumn(
        modifier = modifier
            .background(BackgroundColor),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(8.dp)
    ) {
        item {
            val interactionSource = remember { MutableInteractionSource() }
            val inFocus = interactionSource.collectIsFocusedAsState()
            OutlinedTextField (
                modifier = Modifier.fillMaxWidth(),
                interactionSource = interactionSource,
                value = searchText,
                onValueChange = onSearchTextChange,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null
                    )
                },
                label = {
                    Text(text = "Поиск товаров")
                },
                trailingIcon = {
                    if (inFocus.value && searchText != "") {
                        IconButton(
                            onClick = {
                                onSearchTextChange("")
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Clear search filter"
                            )
                        }
                    }
                }
            )
        }
        items(
            count = products.itemCount,
        ) { index ->
            val item = products[index]
            if (item != null) {
                ProductCard(
                    onEditClick = { itemToChange = item },
                    onDeleteClick = { itemToDelete = item },
                    product = item
                )
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun ProductsListScreenPreview() {
    val data = MutableStateFlow(PagingData.from(
        testData
    )).collectAsLazyPagingItems()

    ProductsListScreen(
        modifier = Modifier.fillMaxSize(),
        searchText = "asd",
        onSearchTextChange = {},
        onItemAmountChange = {_, _ ->},
        onItemDelete = {},
        products = data
    )
}