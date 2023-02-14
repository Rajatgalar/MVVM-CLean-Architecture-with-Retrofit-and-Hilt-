package com.itechnowizard.login_mvvm.presentation.product_details

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.itechnowizard.login_mvvm.data.remote.dto.product_details.ProductDetailDto
import com.itechnowizard.login_mvvm.databinding.ActivityProductDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class ProductDetails : AppCompatActivity() {

    private lateinit var binding: ActivityProductDetailsBinding
    private val productDetailViewModel: ProductDetailViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        productDetailViewModel.state.observe(this, Observer { state ->
            if (state.isLoading) {
                binding.progressBar.visibility = View.VISIBLE
            } else if (state.error.isNotBlank()) {
                binding.progressBar.visibility = View.INVISIBLE
                Toast.makeText(this, "Error : ${state.error}", Toast.LENGTH_SHORT).show()
            } else if (state.products != null) {
                binding.progressBar.visibility = View.INVISIBLE
                setData(state.products)
            }
        })
    }

    private fun setData(products: ProductDetailDto) {
        binding.apply {
            brand.text = "Brand : " + products.brand
            category.text = "Category : " + products.category
            price.text = "Price : " + products.price.toString()
            title.text = products.title
            description.text = products.description
            discountPercentage.text = "Discrount % : " + products.discountPercentage.toString()
            rating.text = "Rating : " + products.rating.toString()
            stock.text = "Stock : " + products.stock.toString()
            viewpager.adapter = SlidingImageAdapter(this@ProductDetails,products.images!!)
            dotsIndicator.attachTo(viewpager)
        }
    }
}