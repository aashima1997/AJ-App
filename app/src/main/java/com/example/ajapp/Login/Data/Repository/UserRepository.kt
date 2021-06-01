package com.example.ajapp.Login.Data.Repository

import androidx.lifecycle.LiveData
import com.example.ajapp.Room.Attendees
import com.example.ajapp.Room.User
import com.example.ajapp.Room.UserDao

class UserRepository(private val userDao: UserDao) {

    suspend fun addUser(user: User) {
         userDao.addUser(user)
    }
    val readAllData1:LiveData<List<Attendees>> = userDao.readAllData1()
    suspend fun addUser1(user1: Attendees) {
        userDao.addUser1(user1)
    }
         fun getUsername(userName: String): User {
            return userDao.getUsername(userName)
        }

    }

