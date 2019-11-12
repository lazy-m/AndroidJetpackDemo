package com.lazy.androidjetpackdemo.LiveData.Model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lazy.androidjetpackdemo.LiveData.User

class NameViewModel : ViewModel() {
     val userData: MutableLiveData<User> = MutableLiveData()

    fun initData(){
        //通过postValue 或 setvalue传输数据；postValue 可后台运行中使用
        userData.value=User()
    }
}
