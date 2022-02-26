package com.example.wsl3application.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.room.Room
import com.example.wsl3application.R
import com.example.wsl3application.database.MainDatabase
import com.example.wsl3application.database.UseDao
import com.example.wsl3application.database.User
import com.example.wsl3application.databinding.ActivityLogin1Binding
import com.example.wsl3application.databinding.ActivityLogin2Binding

class Login2Activity : AppCompatActivity() {
    lateinit var binding: ActivityLogin2Binding
    lateinit var userDao:UseDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogin2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        userDao = Room.databaseBuilder(this , MainDatabase::class.java , getString(R.string.databaseName)).build().getUserDao()

        with(binding){
            nextButton.setOnClickListener {
                val name = nameEditText.text.toString()
                val email = emailEditText.text.toString()
                val username = userNameEditText.text.toString()
                val password = passwordEditText.text.toString()
                val cPassword = cPasswordEditText.text.toString()

                if(name.isNotBlank() && email.isNotBlank() && username.isNotBlank() && password.isNotBlank() && cPassword.isNotBlank()){
                    if (password == cPassword){
                        Thread{
                            val user = User(0 , name , username , email , password)
                            userDao.insert(user)

                            runOnUiThread {
                                Toast.makeText(this@Login2Activity , "User created" , Toast.LENGTH_LONG).show()
                                finish()
                            }
                        }.start()

                    }else
                        Toast.makeText(this@Login2Activity , "Passwords doesn't match!" , Toast.LENGTH_LONG).show()
                }else
                    Toast.makeText(this@Login2Activity , "Fill all inputs" , Toast.LENGTH_LONG).show()
            }
        }

    }
}