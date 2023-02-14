package com.itechnowizard.login_mvvm.presentation.productlist

import com.itechnowizard.login_mvvm.domain.model.Products

data class ProductListState (
    val isLoading: Boolean = false,
    val products: List<Products> = emptyList(),
    val error: String = ""
)