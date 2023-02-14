package com.itechnowizard.login_mvvm.data.remote

import com.itechnowizard.login_mvvm.data.remote.dto.product_details.ProductDetailDto
import com.itechnowizard.login_mvvm.data.remote.dto.product_list.ProductListDto
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductApi {

    @GET("/products")
    suspend fun getAllProduct() : ProductListDto

    @GET("/products/{productId}")
    suspend fun getProductById(@Path("productId") productId : Int) : ProductDetailDto

}