package com.talhakara.yemeksepeti.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.talhakara.yemeksepeti.R
import com.talhakara.yemeksepeti.domain.CategoryDomain

class CategoryAdapter(private val categoryDomains: ArrayList<CategoryDomain>) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_category, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val categoryDomain = categoryDomains[position]
        holder.categoryName.text = categoryDomain.getTitle()

        val picUrl: String = when (position) {
            0 -> {
                holder.mainLayout.background = ContextCompat.getDrawable(holder.itemView.context, R.drawable.cat_background1)
                "cat_1"

            }
            1 -> {
                holder.mainLayout.background = ContextCompat.getDrawable(holder.itemView.context, R.drawable.cat_background2)
                "cat_2"

            }
            2 -> {
                holder.mainLayout.background = ContextCompat.getDrawable(holder.itemView.context, R.drawable.cat_background3)
                "cat_3"

            }
            3 -> {
                holder.mainLayout.background = ContextCompat.getDrawable(holder.itemView.context, R.drawable.cat_background4)
                "cat_4"

            }
            4 -> {
                holder.mainLayout.background = ContextCompat.getDrawable(holder.itemView.context, R.drawable.cat_background5)
                "cat_5"

            }
            else -> ""
        }
        val drawableResourceId = holder.itemView.context.resources.getIdentifier(picUrl, "drawable", holder.itemView.context.packageName)
        // Görünüm öğelerine erişim ve diğer işlemleri burada gerçekleştirin.

        Glide.with(holder.itemView.context)
            .load(drawableResourceId)
            .into(holder.categoryPic)
    }


    override fun getItemCount(): Int {
        return categoryDomains.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val categoryName: TextView = itemView.findViewById(R.id.categoryName)
        val categoryPic: ImageView = itemView.findViewById(R.id.categoryPic)
        val mainLayout: ConstraintLayout = itemView.findViewById(R.id.mainLayout)

        // Görünüm öğelerine erişim ve diğer işlemleri bu iç içe sınıf içinde gerçekleştirin.
    }
}
