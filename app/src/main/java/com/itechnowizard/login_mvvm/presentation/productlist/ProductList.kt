package com.itechnowizard.login_mvvm.presentation.productlist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.itechnowizard.login_mvvm.R
import com.itechnowizard.login_mvvm.databinding.ActivityProductlistBinding
import com.itechnowizard.login_mvvm.presentation.auth.LoginActivity
import com.itechnowizard.login_mvvm.utils.PrefUtils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.runBlocking

@AndroidEntryPoint
class ProductList : AppCompatActivity() {

    private lateinit var binding: ActivityProductlistBinding
    private val viewModel : ProductListViewModel by viewModels()
    private val adapter = ProductListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductlistBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.recyclerView.adapter = adapter
        viewModel.state.observe(this, Observer{ state ->
            if(state.isLoading){
                binding.progressBar.visibility= View.VISIBLE
            }else if(state.error.isNotBlank()){
                binding.progressBar.visibility= View.INVISIBLE
                Toast.makeText(this,"Error : ${state.error}",Toast.LENGTH_SHORT).show()
            }else if(state.products.isNotEmpty()){
                binding.progressBar.visibility= View.INVISIBLE
                adapter.setProductsList(state.products)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater : MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_item,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return  when (item.itemId) {
            R.id.logout -> {
                runBlocking {
                    PrefUtils(this@ProductList).saveLogin(false)
                }
                val mainIntent = Intent(this,LoginActivity::class.java)
                mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                startActivity(mainIntent)
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}