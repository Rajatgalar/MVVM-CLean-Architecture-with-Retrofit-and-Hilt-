package com.itechnowizard.login_mvvm.domain.usecase

import com.itechnowizard.login_mvvm.data.remote.dto.product_list.toProduct
import com.itechnowizard.login_mvvm.domain.model.Products
import com.itechnowizard.login_mvvm.domain.repository.ProductRepository
import com.itechnowizard.login_mvvm.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import java.net.HttpRetryException
import javax.inject.Inject

class ProductListUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    operator fun invoke() : Flow<Resource<List<Products>>> = flow {
        try {
            emit(Resource.Loading())
            val products = repository.getProducts().products!!.map { it.toProduct() }
            emit(Resource.Success(products))
        }catch (e: IOException){
            emit(Resource.Error(e.toString()))
        }catch (e : HttpRetryException){
            emit(Resource.Error("Check Internet"))
        }
    }
}