package com.sahilkalra.coffeecafe.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sahilkalra.coffeecafe.R
import kotlinx.android.synthetic.main.activity_after_forgot.*

class AfterForgot : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_after_forgot)
        val getNum=intent.getStringExtra("mobileNum")
        val getEmailId=intent.getStringExtra("emailId")
        getForgotDetails.text="Mobile Number: "+getNum+"\nEmail Id: "+getEmailId
    }
}