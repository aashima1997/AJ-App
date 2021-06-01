package com.example.ajapp.Room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ajapp.Room.Attendees
import com.example.ajapp.Room.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Query("SELECT * FROM tableLogin")
    fun readAllData():LiveData<List<User>>

    //Attendees class
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser1(user1: Attendees)

    @Query("SELECT * FROM table_Register")
    fun readAllData1():LiveData<List<Attendees>>




    @Query("SELECT * FROM tableLogin WHERE Username LIKE :userName")
     fun getUsername(userName: String): User



}