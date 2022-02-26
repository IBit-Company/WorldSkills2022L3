package com.example.wsl3application.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.room.Room
import com.example.wsl3application.R
import com.example.wsl3application.database.MainDatabase
import com.example.wsl3application.database.UseDao
import com.example.wsl3application.databinding.ActivityLogin1Binding

class Login1Activity : AppCompatActivity() {
    lateinit var  binding: ActivityLogin1Binding
    lateinit var userDao: UseDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogin1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        userDao = Room.databaseBuilder(this , MainDatabase::class.java , getString(R.string.databaseName)).build().getUserDao()


        binding.signUpButton.setOnClickListener {
            startActivity(Intent(this , Login2Activity::class.java))
        }

        binding.nextButton.setOnClickListener {
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            if (email.isNotBlank() && password.isNotBlank()){
                Thread{
                    val user = userDao.getUser(email)

                   runOnUiThread {
                       if (user != null && user.password == password){
                           Toast.makeText(this , "Successfully" , Toast.LENGTH_LONG).show()

                       }else
                           Toast.makeText(this , "username and password doesn't match!" , Toast.LENGTH_LONG).show()
                   }

                }.start()
            }else
                Toast.makeText(this , "Fill all inputs!" , Toast.LENGTH_LONG).show()


        }

    }
}