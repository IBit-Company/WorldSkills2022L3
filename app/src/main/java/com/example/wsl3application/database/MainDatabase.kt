package com.example.wsl3application.database

import android.service.autofill.UserData
import androidx.room.*


@Entity
data class User(
    @PrimaryKey (autoGenerate = true) val id: Int ,
    val name: String,
    val username: String,
    val email :String,
    val password: String
)

@Dao
interface UseDao{

    @Insert
    fun insert(user: User)

    @Query("SELECT * from User WHERE email Like :email")
    fun getUser(email: String): User?


}

@Database(entities = [User::class] , version = 1)
abstract class MainDatabase : RoomDatabase() {
    abstract fun getUserDao(): UseDao
}