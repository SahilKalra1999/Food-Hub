package com.sahilkalra.coffeecafe.adapter

import android.content.Context
import android.os.AsyncTask
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sahilkalra.coffeecafe.R
import com.sahilkalra.coffeecafe.database.ResEntity
import com.sahilkalra.coffeecafe.database.RestaurantDatabase
import com.sahilkalra.coffeecafe.model.Restaurant
import com.squareup.picasso.Picasso

class HomeRecyclerAdapter(val context: Context, private val itemList: ArrayList<Restaurant>) :
    RecyclerView.Adapter<HomeRecyclerAdapter.HomeViewHolder>() {
    class HomeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtRestaurantName: TextView = view.findViewById(R.id.txtRestaurantName)
        val txtRestaurantCost: TextView = view.findViewById(R.id.txtRestaurantCost)
        val txtRestaurantRating: TextView = view.findViewById(R.id.txtRestaurantRating)
        val imgRestaurantImage: ImageView = view.findViewById(R.id.imgRestaurantImage)
        val resContent: LinearLayout = view.findViewById(R.id.resContent)
        val imgFavIcon: ImageView = view.findViewById(R.id.imgFavIcon)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_home_single_row, parent, false)
        return HomeViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val restaurant = itemList[position]
        holder.txtRestaurantName.text = restaurant.resName
        holder.txtRestaurantRating.text = restaurant.resRating
        holder.txtRestaurantCost.text = restaurant.resCost

        Picasso.get().load(restaurant.resImage).error(R.drawable.app_logo)
            .into(holder.imgRestaurantImage)
        holder.resContent.setOnClickListener {
            Toast.makeText(
                context,
                "Clicked on ${holder.txtRestaurantName.text}",
                Toast.LENGTH_LONG
            ).show()
        }

        val resEntity = ResEntity(
            restaurant.id,
            restaurant.resName,
            restaurant.resRating,
            restaurant.resCost,
            restaurant.resImage
        )
        val isFav = DBAsyncTask(context, resEntity, 1).execute().get()

        if (isFav) {
            holder.imgFavIcon.setImageResource(R.drawable.ic_heart_fill)
        } else {
            holder.imgFavIcon.setImageResource(R.drawable.ic_heart_on)
        }

        holder.imgFavIcon.setOnClickListener {
            restaurant.is_selected = !restaurant.is_selected
            if (restaurant.is_selected) {
                DBAsyncTask(context, resEntity, 2).execute()
                holder.imgFavIcon.setImageResource(R.drawable.ic_heart_fill)
            } else {
                DBAsyncTask(context, resEntity, 3).execute()
                holder.imgFavIcon.setImageResource(R.drawable.ic_heart_on)
            }
        }

    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    class DBAsyncTask(val context: Context, val resEntity: ResEntity, val mode: Int) :
        AsyncTask<Void, Void, Boolean>() {
        // mode1-> check DB if the restaurant is favorite or not
        // mode2-> add restaurant to the favorite
        // mode3-> remove restaurant from favorite
        val db = Room.databaseBuilder(context, RestaurantDatabase::class.java, "restaurants-db")
            .fallbackToDestructiveMigration().build()

        override fun doInBackground(vararg params: Void?): Boolean {
            when (mode) {
                1 -> {
                    // check DB if the restaurant is favorite or not
                    val res: ResEntity? = db.resDao().getResByName(resEntity.resName.toString())
                    db.close()
                    return res != null
                }

                2 -> {
                    // add book to the favorite
                    db.resDao().insertRes(resEntity)
                    db.close()
                    return true
                }

                3 -> {
                    // remove book from favorite
                    db.resDao().deleteRes(resEntity)
                    db.close()
                    return true
                }
            }
            return false
        }
    }
}