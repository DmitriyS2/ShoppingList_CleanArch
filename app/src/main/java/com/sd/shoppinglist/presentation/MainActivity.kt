package com.sd.shoppinglist.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.sd.shoppinglist.R

class MainActivity : AppCompatActivity() {

    private val viewModel:MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.shopList.observe(this) {
            Log.d("MyLog", it.toString())
        }

  //      viewModel.getShopList()
    }
}