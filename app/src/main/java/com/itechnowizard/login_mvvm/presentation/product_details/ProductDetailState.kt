package com.itechnowizard.login_mvvm.presentation.product_details

import com.itechnowizard.login_mvvm.data.remote.dto.product_details.ProductDetailDto

data class ProductDetailState (
    val isLoading: Boolean = false,
    val products: ProductDetailDto? = null,
    val error: String = ""
)