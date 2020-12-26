package com.sahilkalra.coffeecafe.fragment

import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.RelativeLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.sahilkalra.coffeecafe.R
import com.sahilkalra.coffeecafe.adapter.FavoriteRecyclerAdapter
import com.sahilkalra.coffeecafe.database.ResEntity
import com.sahilkalra.coffeecafe.database.RestaurantDatabase


class FavoriteRestaurantsFragment : Fragment() {
    lateinit var recyclerFavorite:RecyclerView
    lateinit var progressLayout:RelativeLayout
    lateinit var progressBar: ProgressBar
    lateinit var layoutManager:RecyclerView.LayoutManager
    lateinit var recyclerAdapter: FavoriteRecyclerAdapter
    var dbResList= listOf<ResEntity>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_favorite_restaurants, container, false)
        recyclerFavorite=view.findViewById(R.id.recyclerFavorite)
        progressLayout=view.findViewById(R.id.progressLayout)
        progressBar=view.findViewById(R.id.progressBar)
        layoutManager=GridLayoutManager(activity as Context,2)
        dbResList=RetrieveFavorites(activity as Context).execute().get()

        if (dbResList!=null && activity!=null){
            progressLayout.visibility=View.GONE
            recyclerAdapter=FavoriteRecyclerAdapter(activity as Context, dbResList)
            recyclerFavorite.adapter=recyclerAdapter
            recyclerFavorite.layoutManager=layoutManager
        }
        return view
    }
    class RetrieveFavorites(val context: Context):AsyncTask<Void,Void,List<ResEntity>>(){
        override fun doInBackground(vararg params: Void?): List<ResEntity> {
            val db=Room.databaseBuilder(context,RestaurantDatabase::class.java,"restaurants-db").build()
            return db.resDao().getAllRes()
        }

    }


}