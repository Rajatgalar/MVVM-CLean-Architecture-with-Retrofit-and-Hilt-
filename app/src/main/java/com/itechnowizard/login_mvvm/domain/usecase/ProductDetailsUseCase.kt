package com.itechnowizard.login_mvvm.domain.usecase

import com.itechnowizard.login_mvvm.data.remote.dto.product_details.ProductDetailDto
import com.itechnowizard.login_mvvm.domain.repository.ProductRepository
import com.itechnowizard.login_mvvm.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import java.net.HttpRetryException
import javax.inject.Inject

class ProductDetailsUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    operator fun invoke(productId : Int) : Flow<Resource<ProductDetailDto>> = flow {
        try {
            emit(Resource.Loading())
            val product = repository.getProductById(productId)
            emit(Resource.Success(product))
        }catch (e: IOException){
            emit(Resource.Error(e.toString()))
        }catch (e : HttpRetryException){
            emit(Resource.Error("Check Internet"))
        }
    }
}