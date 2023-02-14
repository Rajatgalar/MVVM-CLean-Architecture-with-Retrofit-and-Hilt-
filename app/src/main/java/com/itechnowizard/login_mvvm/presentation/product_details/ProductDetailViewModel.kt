package com.itechnowizard.login_mvvm.presentation.product_details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itechnowizard.login_mvvm.domain.usecase.ProductDetailsUseCase
import com.itechnowizard.login_mvvm.utils.Constants
import com.itechnowizard.login_mvvm.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val productDetailUseCase: ProductDetailsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = MutableLiveData<ProductDetailState>()
    val state: MutableLiveData<ProductDetailState> = _state

    init {
        savedStateHandle.get<Int>(Constants.PARAM_PRODUCT_ID)?.let { productId ->
            getProductDetail(productId = productId)
        }
    }

    fun getProductDetail(productId: Int) {
        productDetailUseCase(productId).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = ProductDetailState(products = result.data ?: null)
                }
                is Resource.Error -> {
                    _state.value = ProductDetailState(error = result.message ?: "Unexpected error")
                }
                is Resource.Loading -> {
                    _state.value = ProductDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}