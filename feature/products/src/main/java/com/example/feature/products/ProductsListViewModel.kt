package com.example.feature.products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.data.products.data.Product
import com.example.data.products.data.ProductRepo

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch

class ProductsListViewModel(
    val productRepo: ProductRepo
): ViewModel() {
    private val _searchFilter = MutableStateFlow("")
    val searchFilter = _searchFilter.asStateFlow()

    fun updateSearchFilter(text: String) {
        _searchFilter.value = text
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    val pager = _searchFilter.flatMapLatest { filter ->
        Pager(PagingConfig(pageSize = 10)) { productRepo.selectAll(filter) }
            .flow
            .cachedIn(viewModelScope)
    }

    fun changeAmountOfItems(product: Product, newAmount: Int) = viewModelScope.launch {
        productRepo.update(product.copy(amount = newAmount))
    }

    fun deleteProduct(product: Product) = viewModelScope.launch {
        productRepo.delete(product)
    }
}