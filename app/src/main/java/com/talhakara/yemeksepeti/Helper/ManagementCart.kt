package com.talhakara.yemeksepeti.Helper

import android.content.Context
import android.widget.Toast
import com.talhakara.yemeksepeti.Domain.FoodDomain
import com.talhakara.yemeksepeti.Interface.ChangeNumberItemsListener

class ManagementCart(private val context: Context) {
    private val tinyDB = TinyDB(context)
    val foodList = ArrayList<FoodDomain>()

    fun insertFood(item: FoodDomain) {
        val listFood = getListCart()
        var existAlready = false
        var n = 0
        for (i in listFood.indices) {
            if (listFood[i].title == item.title) {
                existAlready = true
                n = i
                break
            }
        }

        if (existAlready) {
            listFood[n].numberInCard=item.numberInCard
        } else {
            listFood.add(item)
        }
        tinyDB.putObject("cartlist", listFood)
        Toast.makeText(context, "added to Your Cart", Toast.LENGTH_SHORT).show()
    }

    fun getListCart(): ArrayList<FoodDomain> {
        return tinyDB.getListObject("cartlist", FoodDomain::class.java)
    }

    fun plusNumberFood(listFood: ArrayList<FoodDomain>, position: Int, changeNumberItemsListener: ChangeNumberItemsListener) {

        listFood.get(position).numberInCard=listFood.get(position).numberInCard + 1

        tinyDB.putListObject("CartList",listFood)
        changeNumberItemsListener.changed()

    }

    fun minusNumberFood(listFood: ArrayList<FoodDomain>, position: Int, changeNumberItemsListener: ChangeNumberItemsListener) {

        if(listFood.get(position).numberInCard==1) {
           listFood.removeAt(position)
        }else{
            listFood.get(position).numberInCard = listFood.get(position).numberInCard - 1
        }
        tinyDB.putObject("CartList",listFood)
        changeNumberItemsListener.changed()
    }

    fun getTotalFee(): Double {
        val listFood: ArrayList<FoodDomain> = getListCart()

        var fee = 0.0
        for (i in 0 until listFood.size) {
            fee += listFood[i].fee * listFood[i].numberInCard
        }
        return fee
    }

}

