package com.talhakara.yemeksepeti.Adapter

import android.content.Intent
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.talhakara.yemeksepeti.Domain.FoodDomain
import com.talhakara.yemeksepeti.R
import com.talhakara.yemeksepeti.View.ShowDetailActivity

class PopulerAdapter(private val populerFood: ArrayList<FoodDomain>) : RecyclerView.Adapter<PopulerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_populer, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val categoryDomain = populerFood[position]
        holder.title.text = categoryDomain.title
        holder.fee.setText(populerFood.get(position).fee.toString())




        val drawableResourceId = holder.itemView.context.resources.getIdentifier(populerFood.get(position).pic, "drawable", holder.itemView.context.packageName)
        // Görünüm öğelerine erişim ve diğer işlemleri burada gerçekleştirin.

        Glide.with(holder.itemView.context)
            .load(drawableResourceId)
            .into(holder.pic)

        holder.addBtn.setOnClickListener {
            val intent = Intent(holder.itemView.context, ShowDetailActivity::class.java)

            intent.putExtra("foodObject",populerFood.get(position))
            holder.itemView.context.startActivity(intent)
        }
    }


    override fun getItemCount(): Int {
        return populerFood.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.populertitle)
        val pic: ImageView = itemView.findViewById(R.id.pic)
        val fee:TextView = itemView.findViewById(R.id.fee)
        val addBtn:TextView=itemView.findViewById(R.id.addBtn)

        // Görünüm öğelerine erişim ve diğer işlemleri bu iç içe sınıf içinde gerçekleştirin.
    }
}
