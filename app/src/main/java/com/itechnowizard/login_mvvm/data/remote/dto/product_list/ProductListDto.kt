package com.itechnowizard.login_mvvm.data.remote.dto.product_list

data class ProductListDto(
    val limit: Int?,
    val products: List<Product>?,
    val skip: Int?,
    val total: Int?
)