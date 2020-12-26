package com.sahilkalra.coffeecafe.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.sahilkalra.coffeecafe.R

class ForgotPassword : AppCompatActivity() {
    lateinit var etNewMobNumber:EditText
    lateinit var etNewEmail:EditText
    lateinit var btnNext:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)
        etNewMobNumber=findViewById(R.id.etNewMobNumber)
        etNewEmail=findViewById(R.id.etNewEmail)
        btnNext=findViewById(R.id.btnNext)
        btnNext.setOnClickListener {
            val mobNum=etNewMobNumber.text.toString()
            val emailId=etNewEmail.text.toString()
            if (mobNum.length==10){
                val afterForgot=Intent(this@ForgotPassword, AfterForgot::class.java)
                afterForgot.putExtra("mobileNum",mobNum)
                afterForgot.putExtra("emailId",emailId)
                startActivity(afterForgot)
                etNewMobNumber.text=null
                etNewEmail.text=null
            }else{
                Toast.makeText(this@ForgotPassword,"Please enter a 10 digits mobile number",Toast.LENGTH_LONG).show()
            }

        }
    }
}