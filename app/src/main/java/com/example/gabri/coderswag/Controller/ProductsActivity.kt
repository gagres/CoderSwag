package com.example.gabri.coderswag.Controller

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import com.example.gabri.coderswag.Constants.EXTRA_CATEGORY_NAME
import com.example.gabri.coderswag.R

class ProductsActivity : AppCompatActivity() {

    var categoryName: String? = ""

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

        categoryName = savedInstanceState?.getString(EXTRA_CATEGORY_NAME)

        Toast.makeText(this, categoryName, Toast.LENGTH_SHORT).show()
    }
}
