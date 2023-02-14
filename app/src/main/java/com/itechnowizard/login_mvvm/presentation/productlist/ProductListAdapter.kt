package com.itechnowizard.login_mvvm.presentation.productlist

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.itechnowizard.login_mvvm.databinding.ProductListBinding
import com.itechnowizard.login_mvvm.domain.model.Products
import com.itechnowizard.login_mvvm.presentation.product_details.ProductDetails
import com.itechnowizard.login_mvvm.utils.Constants

class ProductListAdapter() : RecyclerView.Adapter<ProductListAdapter.MainViewHolder>() {

    var productList = mutableListOf<Products>()

    fun setProductsList(productList : List<Products>) {
        this.productList = productList.toMutableList()
        notifyDataSetChanged()
    }

    inner class MainViewHolder(val binding: ProductListBinding)
        : RecyclerView.ViewHolder(binding.root){
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ProductListBinding.inflate(inflater,parent,false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.binding.title.text = this.productList[position].title
        holder.binding.description.text = this.productList[position].description
        holder.binding.price.text = "Price : "+ this.productList[position].price.toString()

        Glide.with(holder.itemView.context).load(this.productList[position].thumbnail).into(holder.binding.ivThumbnail);

        holder.itemView.setOnClickListener {
            holder.itemView.context.startActivity(
                Intent(holder.itemView.context, ProductDetails::class.java)
                .putExtra(Constants.PARAM_PRODUCT_ID,this.productList[position].id))
        }
    }

    override fun getItemCount(): Int = this.productList.size
}