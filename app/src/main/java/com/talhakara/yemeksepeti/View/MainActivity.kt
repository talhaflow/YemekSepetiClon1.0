package com.talhakara.yemeksepeti.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.talhakara.yemeksepeti.Adapter.CategoryAdapter
import com.talhakara.yemeksepeti.Adapter.PopulerAdapter
import com.talhakara.yemeksepeti.Domain.FoodDomain
import com.talhakara.yemeksepeti.R
import com.talhakara.yemeksepeti.domain.CategoryDomain

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: RecyclerView.Adapter<*>
    private lateinit var adapter2: RecyclerView.Adapter<*>

    private lateinit var recyclerViewCategoryList: RecyclerView
    private lateinit var recyclerViewPopulerList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerViewCategory()
        recyclerViewPopular()
        bottomNavigation()
    }

    private fun bottomNavigation(){
        val floatingActionButton = findViewById<FloatingActionButton>(R.id.cartBtn)
        val homeBtn = findViewById<LinearLayout>(R.id.homeBtn1)

        floatingActionButton.setOnClickListener {
            startActivity(Intent(this, CartListActivity::class.java))
        }

        homeBtn.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

    }

    private fun recyclerViewCategory() {
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewCategoryList = findViewById(R.id.recyclerView)
        recyclerViewCategoryList.layoutManager = linearLayoutManager


        var category = ArrayList<CategoryDomain>()
        category.add(CategoryDomain("pizza", "cat_1"))
        category.add(CategoryDomain("Burger","cat_2"))
        category.add(CategoryDomain("HotDog", "cat_3"))
        category.add(CategoryDomain("Drink","cat_4"))
        category.add(CategoryDomain("donut", "cat_5"))

        //adapter=new CategoryAdapter(category)
        adapter = CategoryAdapter(category)
        recyclerViewCategoryList.adapter = adapter

    }

    private fun recyclerViewPopular(){
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewPopulerList = findViewById(R.id.PopulerRecyclerView)
        recyclerViewPopulerList.layoutManager = linearLayoutManager



        var foodList = ArrayList<FoodDomain>()
        foodList.add(FoodDomain("pepperoni pizza","pizza1","slices pepperoni ,mozzerella cheese,fresh oregano,ground black pepper,pizza sauce",9.75))
        foodList.add(FoodDomain("Cheese Burger","burger","beef, gouda cheese,special sauce,lettuce,tomato",8.80))
        foodList.add(FoodDomain("Vegetable Pizza","pizza2","olive oil,vegtable oil,pitted kalamata,cherry tomato,fresh oregano,basil",8.5))

        adapter2=PopulerAdapter(foodList)
        recyclerViewPopulerList.adapter=adapter2

    }


}
