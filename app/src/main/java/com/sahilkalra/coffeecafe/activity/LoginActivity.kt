package com.sahilkalra.coffeecafe.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.sahilkalra.coffeecafe.R

class LoginActivity : AppCompatActivity(){
    lateinit var etMobileNumber:EditText
    lateinit var etPassword: EditText
    lateinit var btnLogin:Button
    lateinit var txtForgotPassword: TextView
    lateinit var txtRegister:TextView
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences=getSharedPreferences(getString(R.string.login_preferences),Context.MODE_PRIVATE)
        val isLoggedIn=sharedPreferences.getBoolean("isLoggedIn",false)
        if (isLoggedIn){
            val welcomeActivity = Intent(this@LoginActivity, WelcomeActivity::class.java)
            startActivity(welcomeActivity)
            finish()
        }

        setContentView(R.layout.activity_login)

        btnLogin=findViewById(R.id.btnLogin)
        txtForgotPassword=findViewById(R.id.txtForgotPassword)
        txtRegister=findViewById(R.id.txtRegister)


        btnLogin.setOnClickListener {
            etMobileNumber=findViewById(R.id.etMobileNumber)
            etPassword=findViewById(R.id.etPassword)
            var mobileNumber=etMobileNumber.text.toString()
            var password=etPassword.text.toString()
            if(mobileNumber.length==10) {

                if (password.length >= 8) {

                    val welcomeActivity = Intent(this@LoginActivity, WelcomeActivity::class.java)
                    welcomeActivity.putExtra("MobNumber", mobileNumber)
                    welcomeActivity.putExtra("Password", password)
                    savePreferences()
                    startActivity(welcomeActivity)


                } else {
                    Toast.makeText(
                        this@LoginActivity,
                        "Plz enter a password of  minimum 8 characters",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }else
            {
                Toast.makeText(this@LoginActivity,"Plz enter a valid 10 digits mobile number",Toast.LENGTH_LONG).show()
            }
            
            

        }
        txtForgotPassword.setOnClickListener {
            val forgetScreen=Intent(this@LoginActivity, ForgotPassword::class.java)
            startActivity(forgetScreen)
        }
        txtRegister.setOnClickListener {
            val onRegisterPage=Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(onRegisterPage)
        }


    }
    fun savePreferences(){
        sharedPreferences.edit().putBoolean("isLoggedIn",true).apply()
    }

    override fun onPause() {
        super.onPause()
        finish()
    }

    }




