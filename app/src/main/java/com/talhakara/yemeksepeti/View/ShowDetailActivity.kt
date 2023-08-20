package com.talhakara.yemeksepeti.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.talhakara.yemeksepeti.Domain.FoodDomain
import com.talhakara.yemeksepeti.Helper.ManagementCart
import com.talhakara.yemeksepeti.R



class ShowDetailActivity : AppCompatActivity() {
    private lateinit var addToCartBtn:TextView
    private lateinit var titleTV:TextView
    private lateinit var feeTV:TextView
    private lateinit var descriptionTV:TextView
    private lateinit var numberOrderTV:TextView
    private lateinit var plusBtn:ImageView
    private lateinit var minusBtn:ImageView
    private lateinit var picFood:ImageView
    private lateinit var foodObject: FoodDomain
    var numberOrder:Int=1

    private var managementCart: ManagementCart? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_detail)
        managementCart = ManagementCart(this)

        initView()
        getBundle()
    }

    private fun getBundle() {
        foodObject = intent.getSerializableExtra("foodObject") as FoodDomain

        val drawableResourceId = resources.getIdentifier(foodObject.pic, "drawable", packageName)
        Glide.with(this)
            .load(drawableResourceId)
            .into(picFood)

        titleTV.setText(foodObject.title)
        feeTV.setText("$"+foodObject.fee)
        descriptionTV.setText(foodObject.description)
        numberOrderTV.setText(numberOrder.toString())


        plusBtn.setOnClickListener {
             numberOrder++
            numberOrderTV.setText(numberOrder.toString())
        }
        minusBtn.setOnClickListener {
            if(numberOrder>0){
                numberOrder--
                numberOrderTV.setText(numberOrder.toString())
            }
        }


        addToCartBtn.setOnClickListener {
            foodObject.numberInCard=numberOrder

            if (managementCart != null) {
                managementCart!!.insertFood(foodObject)
            }
        }

    }

    private fun initView() {
        addToCartBtn=findViewById(R.id.addToCartBtn)
        titleTV=findViewById(R.id.showDetailTitle_tv)
        //comeback
        feeTV=findViewById(R.id.priceTxt)
        descriptionTV=findViewById(R.id.descriptionTV)
        numberOrderTV=findViewById(R.id.numberOrderTV)
        plusBtn=findViewById(R.id.plusBtn)
        minusBtn=findViewById(R.id.minusBtn)
        picFood=findViewById(R.id.showDetailFoodPic)
    }
}