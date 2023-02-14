package com.itechnowizard.login_mvvm.data.repository

import com.itechnowizard.login_mvvm.data.remote.ProductApi
import com.itechnowizard.login_mvvm.data.remote.dto.product_details.ProductDetailDto
import com.itechnowizard.login_mvvm.data.remote.dto.product_list.ProductListDto
import com.itechnowizard.login_mvvm.domain.repository.ProductRepository
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val productApi: ProductApi
) : ProductRepository{

    override suspend fun getProducts(): ProductListDto {
        return productApi.getAllProduct()
    }

    override suspend fun getProductById(productId : Int): ProductDetailDto {
        return  productApi.getProductById(productId = productId)
    }
}