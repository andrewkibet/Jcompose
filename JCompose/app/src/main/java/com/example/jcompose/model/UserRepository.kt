package com.example.jcompose.network.com.example.jcompose.model

import android.service.autofill.UserData
import kotlinx.coroutines.delay

class UserRepository {
    suspend fun fetchUserData(): com.example.jcompose.network.com.example.jcompose.model.UserData {
        delay(2000)
        return UserData("SS",25)

    }
    }
