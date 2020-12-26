package com.sahilkalra.coffeecafe.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.sahilkalra.coffeecafe.R

class RegisterActivity : AppCompatActivity() {
    lateinit var etName:EditText
    lateinit var etEmail:EditText
    lateinit var etMobNumber:EditText
    lateinit var etDeliveryAddress:EditText
    lateinit var etRegPassword:EditText
    lateinit var etConfirmPassword:EditText
    lateinit var btnRegister:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        etName=findViewById(R.id.etName)
        etEmail=findViewById(R.id.etEmail)
        etMobNumber =findViewById(R.id.etMobNumber)
        etDeliveryAddress=findViewById(R.id.etDeliveryAddress)
        etRegPassword=findViewById(R.id.etRegPassword)
        etConfirmPassword=findViewById(R.id.etConfirmPassword)
        btnRegister=findViewById(R.id.btnRegister)
    }
}