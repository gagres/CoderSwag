package com.example.gabri.coderswag.Controller

import android.content.res.Configuration
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import com.example.gabri.coderswag.Adapters.ProductsAdapter
import com.example.gabri.coderswag.Constants.EXTRA_CATEGORY_NAME
import com.example.gabri.coderswag.R
import com.example.gabri.coderswag.Services.DataService
import kotlinx.android.synthetic.main.activity_products.*

class ProductsActivity : AppCompatActivity() {

    var categoryName: String? = ""
    lateinit var adapter : ProductsAdapter

    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        super.onSaveInstanceState(outState, outPersistentState)

        outState?.putString(EXTRA_CATEGORY_NAME, categoryName)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)

        if (savedInstanceState != null) {
            categoryName = savedInstanceState.getString(EXTRA_CATEGORY_NAME)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)

        categoryName = intent.getStringExtra(EXTRA_CATEGORY_NAME)
        categoryNameText.text = categoryName
        Toast.makeText(this, categoryName, Toast.LENGTH_SHORT).show()

        adapter = ProductsAdapter(this, DataService.getProducts(categoryName))
        productListView.adapter = adapter

        val orientation = resources.configuration.orientation

        var spanCount = 2
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            spanCount = 3
        }

        val screenSize = resources.configuration.screenWidthDp

        if (screenSize > 720) {
            spanCount = 3
        }

        val layoutManager = GridLayoutManager(this, spanCount)
        productListView.layoutManager = layoutManager
        productListView.hasFixedSize()
    }
}
