package com.lazy.androidjetpackdemo.LiveData

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.lazy.androidjetpackdemo.LiveData.Model.NameViewModel
import com.lazy.androidjetpackdemo.R
import kotlinx.android.synthetic.main.activity_livedata.*

class LiveDataActivity : AppCompatActivity() {
    // 获取ViewModel
//    model = ViewModelProviders.of(this).get(NameViewModel::class.java)
//    val  viewModel :NameViewModel by viewModels()
    val viewModel by viewModels<NameViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_livedata)

        // 1.当数据有变化时候重新设置 TextView的Text(数据变化监听)
        val  nameObserver = Observer<User> { user ->
            nameTextView.text=user.name
        }
        // 2 .观察LiveData，并以LifecycleOwner和观察者的身份传入此活动。
    viewModel.userData.observe(this, nameObserver)

    //优化1 ， 2 步骤
    viewModel.userData.observe(this, Observer { user ->
        nameTextView.text=user.name
    })

        //点击按钮时改变数据内容
        bt_shiy.setOnClickListener {
            viewModel.initData()
        }
    }
}
