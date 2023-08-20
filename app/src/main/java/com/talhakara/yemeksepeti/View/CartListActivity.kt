package com.talhakara.yemeksepeti.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.talhakara.yemeksepeti.Adapter.CartListAdapter
import com.talhakara.yemeksepeti.Helper.ManagementCart
import com.talhakara.yemeksepeti.Interface.ChangeNumberItemsListener
import com.talhakara.yemeksepeti.R


class CartListActivity : AppCompatActivity() {
    private lateinit var adapter: CartListAdapter
    private lateinit var recyclerViewList: RecyclerView
     lateinit var managementCart: ManagementCart
    private lateinit var totalFeeTxt: TextView
   // private lateinit var taxTxt: TextView
    private lateinit var deliveryTxt: TextView
    private lateinit var totalTxt: TextView
    private lateinit var emptyTxt: TextView
    private var tax: Double = 0.0
    private lateinit var scrollView: ScrollView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart_list)

        managementCart = ManagementCart(this)

        initView()
        initList()
        CalculateCart()
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

    private fun initView() {
       // recyclerViewList=findViewById(R.id.recyclerView)
        totalFeeTxt=findViewById(R.id.itemtotalDegerTxt)
        deliveryTxt=findViewById(R.id.deliveryServiceTxt)
        totalTxt=findViewById(R.id.totalTxt)
        emptyTxt=findViewById(R.id.emptyTxt)
        scrollView=findViewById(R.id.scrollView3)
        recyclerViewList=findViewById(R.id.cartView)
    }

    private fun initList(){
        val linearLayoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        recyclerViewList.layoutManager = LinearLayoutManager(this)

         adapter = CartListAdapter(
            managementCart.getListCart(),
             this,
            object : ChangeNumberItemsListener {
                override fun changed() {
                    CalculateCart() // CalculateCart fonksiyonunun doğru bir şekilde tanımlandığından emin olun
                }
            }
        )


        recyclerViewList.adapter = adapter

        if(managementCart.getListCart().isEmpty()){
            emptyTxt.visibility = View.VISIBLE
            scrollView.visibility=View.GONE
        }else{
            emptyTxt.visibility=View.GONE
            scrollView.visibility=View.VISIBLE
        }

    }

    private fun CalculateCart(){
        val percentTax = 0.02
        val delivery = 10.0


        tax = (Math.round((managementCart.getTotalFee()*percentTax)*100)/100.0)
        val total = ((managementCart.getTotalFee() + tax + delivery) * 100)/ 100.0
        val itemTotal=Math.round(managementCart.getTotalFee()*100)/100

        totalFeeTxt.setText("$"+itemTotal)
        deliveryTxt.setText("$"+delivery)
        totalTxt.setText("$"+total)


    }


}