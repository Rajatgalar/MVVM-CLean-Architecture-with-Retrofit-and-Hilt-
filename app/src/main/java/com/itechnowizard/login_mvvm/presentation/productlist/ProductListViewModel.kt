package com.itechnowizard.login_mvvm.presentation.productlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itechnowizard.login_mvvm.domain.usecase.ProductListUseCase
import com.itechnowizard.login_mvvm.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class ProductListViewModel @Inject constructor(
    private val productListUseCase: ProductListUseCase
) : ViewModel() {
    private val _state = MutableLiveData<ProductListState>()
    val state: MutableLiveData<ProductListState> = _state

    init {
        getProductList()
    }

    fun getProductList() {
        productListUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = ProductListState(products = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = ProductListState(error = result.message ?: "Unexpected error")
                }
                is Resource.Loading -> {
                    _state.value = ProductListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}