package com.lazy.androidjetpackdemo.LiveData.Model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NameViewModel : ViewModel() {
     val currentName: MutableLiveData<User> by  lazy {
        MutableLiveData<User>()
    }

    val user =MutableLiveData<User>()
}

data class  User (
    val  name: String="Jie",
    val  age : String ="20"
)