package com.talhakara.yemeksepeti.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.talhakara.yemeksepeti.Domain.FoodDomain
import com.talhakara.yemeksepeti.Helper.ManagementCart
import com.talhakara.yemeksepeti.Interface.ChangeNumberItemsListener
import com.talhakara.yemeksepeti.R


class CartListAdapter(
    private val foodDomainList: ArrayList<FoodDomain>,
    private val context: Context,
    private val changeNumberItemsListener: ChangeNumberItemsListener
) : RecyclerView.Adapter<CartListAdapter.ViewHolder>() {
    val managementCart = ManagementCart(context) // Context üzerinden managementCart oluşturuluyor

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView? = null
        var feeEachItem: TextView? = null
        var pic: ImageView? = null
        var plusItem: ImageView? = null
        var minusItem: ImageView? = null
        var totalEachItem: TextView? = null
        var num: TextView? = null

            init {
                title = itemView.findViewById(R.id.titleTxt)
                feeEachItem = itemView.findViewById(R.id.feeEachItem)
                pic = itemView.findViewById(R.id.picCart)
                plusItem = itemView.findViewById(R.id.plusCartBtn)
                minusItem = itemView.findViewById(R.id.minusBtn)
                totalEachItem = itemView.findViewById(R.id.totalEachItem)
             //   num = itemView.findViewById(R.id.numberItemTxt)
            }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_cart, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.title?.setText(foodDomainList.get(position).title)
        holder.feeEachItem?.setText((foodDomainList.get(position).fee).toString())
        holder.totalEachItem?.setText((Math.round(foodDomainList.get(position).numberInCard*foodDomainList.get(position).fee*100)/100).toString())
        holder.num?.setText(foodDomainList.get(position).numberInCard.toString())

        val drawableResourceId = holder.itemView.context.resources.getIdentifier(
            foodDomainList[position].pic,
            "drawable",
            holder.itemView.context.packageName
        )

        holder.pic?.let {
            Glide.with(holder.itemView.context)
                .load(drawableResourceId)
                .into(it)
        }

        holder.plusItem?.setOnClickListener {
            managementCart.plusNumberFood(foodDomainList, position, object : ChangeNumberItemsListener {
                override fun changed() {
                    notifyDataSetChanged()
                    changeNumberItemsListener.changed()
                }
            })
        }

        holder.minusItem?.setOnClickListener {
            managementCart.minusNumberFood(foodDomainList, position, object : ChangeNumberItemsListener {
                override fun changed() {
                    notifyDataSetChanged()
                    changeNumberItemsListener.changed()
                }
            })
        }

    }

    override fun getItemCount(): Int {
        return foodDomainList.size
    }
}
