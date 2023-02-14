package com.itechnowizard.login_mvvm.di

import com.itechnowizard.login_mvvm.data.remote.ProductApi
import com.itechnowizard.login_mvvm.data.repository.ProductRepositoryImpl
import com.itechnowizard.login_mvvm.domain.repository.ProductRepository
import com.itechnowizard.login_mvvm.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesRetrofitApi(): ProductApi = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ProductApi::class.java)

    @Provides
    @Singleton
    fun providesProductRepository(api : ProductApi) : ProductRepository{
        return ProductRepositoryImpl(api)
    }

}