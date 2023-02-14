package com.itechnowizard.login_mvvm.data.remote.dto.product_list

import com.itechnowizard.login_mvvm.domain.model.Products

data class Product(
    val brand: String?,
    val category: String?,
    val description: String?,
    val discountPercentage: Double?,
    val id: Int?,
    val images: List<String>?,
    val price: Int?,
    val rating: Double?,
    val stock: Int?,
    val thumbnail: String?,
    val title: String?
)

fun Product.toProduct() : Products = Products(description, id, price, thumbnail, title)