package com.example.ajapp.Login.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.ajapp.Room.Attendees
import com.example.ajapp.Room.User
import com.example.ajapp.Room.UserDatabase
import com.example.ajapp.Login.Data.Repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel (application: Application): AndroidViewModel(application) {
     val repository: UserRepository
    var readAllData1: LiveData<List<Attendees>>
    init {
        val userDao = UserDatabase.getDatabase(application)?.userDao()
        repository = UserRepository(userDao!!)
        readAllData1=repository.readAllData1
    }

    fun addUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)

        }
    }

    fun addUser1(user1: Attendees) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser1(user1)

        }
    }

    fun getUsername(userName: String): User {
      return repository.getUsername(userName)
    }

}
