package com.lazy.androidjetpackdemo.LiveData

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.lazy.androidjetpackdemo.LiveData.Model.NameViewModel
import com.lazy.androidjetpackdemo.LiveData.Model.User
import com.lazy.androidjetpackdemo.R
import kotlinx.android.synthetic.main.activity_livedata.*

class LiveDataActivity : AppCompatActivity() {
//    private lateinit var model: NameViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_livedata)
        // 获取ViewModel
//        model = ViewModelProviders.of(this).get(NameViewModel::class.java)
//    val  viewModel :NameViewModel by viewModels()
    val viewModel by viewModels<NameViewModel>()
        //当数据有变化时候重新设置 TextView的Text(数据变化监听)
        val  nameObserver = Observer<User> { user ->
            nameTextView.text=user.name
        }
        //观察LiveData，并以LifecycleOwner和观察者的身份传入此活动。
    viewModel.currentName.observe(this, nameObserver)
//        model.user.observe(this, nameObserver)

        //点击按钮时改变数据内容
        bt_shiy.setOnClickListener {
            val anotherName = "John Doe"
            //通过postValue 或 setvalue传输数据；postValue 可后台运行中使用
            viewModel.currentName.setValue(User())
        }
    }
}
