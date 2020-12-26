package com.sahilkalra.coffeecafe.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sahilkalra.coffeecafe.R
import com.sahilkalra.coffeecafe.database.ResEntity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.recycler_favorite_single_row.view.*
import java.nio.channels.Pipe

class FavoriteRecyclerAdapter(val context: Context,val resList:List<ResEntity>):RecyclerView.Adapter<FavoriteRecyclerAdapter.FavoriteViewHolder>() {
    class FavoriteViewHolder(view: View): RecyclerView.ViewHolder(view){
        val txtResName:TextView=view.findViewById(R.id.txtFavResName)
        val txtResPrice:TextView=view.findViewById(R.id.txtFavResPrice)
        val txtResRating:TextView=view.findViewById(R.id.txtFavResRating)
        val imgResImage:ImageView=view.findViewById(R.id.imgFavResImage)
        val favContent:LinearLayout=view.findViewById(R.id.favContent)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
         val view=LayoutInflater.from(parent.context).inflate(R.layout.recycler_favorite_single_row,parent,false)
        return FavoriteViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
   val restaurant=resList[position]
        holder.txtResName.text=restaurant.resName
        holder.txtResRating.text=restaurant.resRating
        holder.txtResPrice.text=restaurant.resCost
        Picasso.get().load(restaurant.resImage).error(R.drawable.app_logo).into(holder.imgResImage)
    }

    override fun getItemCount(): Int {
  return resList.size
    }
}