package com.itechnowizard.login_mvvm.domain.repository

import com.itechnowizard.login_mvvm.data.remote.dto.product_details.ProductDetailDto
import com.itechnowizard.login_mvvm.data.remote.dto.product_list.ProductListDto


interface ProductRepository {

    suspend fun getProducts() : ProductListDto

    suspend fun getProductById(productId : Int) : ProductDetailDto
}