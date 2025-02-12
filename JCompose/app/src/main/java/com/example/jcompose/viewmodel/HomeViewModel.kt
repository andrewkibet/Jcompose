package com.example.jcompose.network.com.example.jcompose.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.jcompose.network.com.example.jcompose.model.UserData
import com.example.jcompose.network.com.example.jcompose.model.UserRepository
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val userRepository: UserRepository = UserRepository()
    private val _userData = MutableLiveData<UserData>()
    val userData: LiveData<UserData> = _userData

    fun getUserData() {
        viewModelScope.launch {
            try {
                val userResult = userRepository.fetchUserData()
                _userData.postValue(userResult)
            }
            catch (e : Exception){
                Log.e("HomeViewmodel", "Error fetching user data",e)

            }

        }
    }
}
