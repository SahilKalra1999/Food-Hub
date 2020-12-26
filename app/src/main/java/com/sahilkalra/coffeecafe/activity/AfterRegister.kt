package com.sahilkalra.coffeecafe.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sahilkalra.coffeecafe.R
import kotlinx.android.synthetic.main.activity_after_register.*

class AfterRegister : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_after_register)
        val getRegName=intent.getStringExtra("Registered Name")
        val getEmail=intent.getStringExtra("Registered Email")
        val getMob=intent.getStringExtra("Registered MobNumber")
        val getDeliveryAddress=intent.getStringExtra("Delivery Address")
        val getPassword=intent.getStringExtra("Registered Password")
        val getConfirmPassword=intent.getStringExtra("Confirm Password")
        txtRegisteredDetails.text="Name: "+getRegName+"\nEmail Id: "+getEmail+"\nMobile No: "+getMob+"\nDelivery Address: "+getDeliveryAddress+"\nPassword: "+getPassword+"\nConfirm Password: "+getConfirmPassword
    }
}