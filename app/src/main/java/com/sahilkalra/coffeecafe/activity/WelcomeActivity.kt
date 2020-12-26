package com.sahilkalra.coffeecafe.activity

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.app.ActivityCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.sahilkalra.coffeecafe.*
import com.sahilkalra.coffeecafe.fragment.*

class WelcomeActivity : AppCompatActivity() {
    lateinit var drawerLayout:DrawerLayout
    lateinit var coordinatorLayout: CoordinatorLayout
    lateinit var toolbar: Toolbar
    lateinit var frameLayout: FrameLayout
    lateinit var navigationView: NavigationView
    lateinit var sharedPreferences: SharedPreferences
    var previousMenuItem : MenuItem?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences=getSharedPreferences(getString(R.string.login_preferences),Context.MODE_PRIVATE)
        setContentView(R.layout.activity_welcome)
        drawerLayout=findViewById(R.id.drawerLayout)
        coordinatorLayout=findViewById(R.id.coordinatorLayout)
        toolbar=findViewById(R.id.toolbar)
        frameLayout=findViewById(R.id.frameLayout)
        navigationView=findViewById(R.id.navigationView)
        setUpToolbar()
        openHome()

        val actionBarDrawerToggle= ActionBarDrawerToggle(this@WelcomeActivity
            ,drawerLayout
            , R.string.open_drawer, R.string.close_drawer
        )
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
        navigationView.setNavigationItemSelectedListener {
            if (previousMenuItem!=null){
                previousMenuItem?.isChecked=false
            }
            it.isCheckable=true
            it.isChecked=true
            previousMenuItem=it

            when(it.itemId){
                R.id.home ->{
                  openHome()
                }
                R.id.myProfile ->{
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frameLayout, MyProfileFragment())

                        .commit()
                    supportActionBar?.title="My Profile"
                    drawerLayout.closeDrawers()
                }
                R.id.favoriteRestaurants ->{
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frameLayout, FavoriteRestaurantsFragment())

                        .commit()
                    supportActionBar?.title="Favorite Restaurants"
                    drawerLayout.closeDrawers()
                            }
                R.id.faqs ->{
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frameLayout, FaqsFragment())

                        .commit()
                    supportActionBar?.title="FAQs"
                    drawerLayout.closeDrawers()
                }
                R.id.logout ->{
                    val alertDialog=AlertDialog.Builder(this@WelcomeActivity)
                    alertDialog.setTitle("Exit")
                    alertDialog.setMessage("Do you want to exit the app ?")
                    alertDialog.setPositiveButton("Exit"){text,Listener->
                        ActivityCompat.finishAffinity(this@WelcomeActivity)
                    }
                    alertDialog.setNegativeButton("Cancel"){
                        text,Listener->
                    }
                    alertDialog.create()
                    alertDialog.show()
                     }
            }
            return@setNavigationItemSelectedListener true
        }

    }
    fun setUpToolbar(){
        setSupportActionBar(toolbar)
        supportActionBar?.title="Toolbar Title"
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id=item.itemId
        if (id==android.R.id.home){
            drawerLayout.openDrawer(GravityCompat.START)
        }
        return super.onOptionsItemSelected(item)
    }
    fun openHome(){
        val fragment= HomeFragment()
        val transaction=supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout,fragment).commit()
        supportActionBar?.title="Home"
        drawerLayout.closeDrawers()
        navigationView.setCheckedItem(R.id.home)
    }

    override fun onBackPressed() {
        val frag=supportFragmentManager.findFragmentById(R.id.frameLayout)
        when(frag){
            !is HomeFragment -> openHome()
            else -> super.onBackPressed()
        }
    }
}