package com.lazy.androidjetpackdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.lazy.androidjetpackdemo.LiveData.LiveDataActivity
import com.lazy.androidjetpackdemo.Navigation.NavigationActivity
import com.lazy.androidjetpackdemo.Paging.PagingActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bt_LiveData.setOnClickListener { startActivity<LiveDataActivity>() }
        bt_Navigation.setOnClickListener { startActivity<NavigationActivity>() }
        bt_Paging.setOnClickListener { startActivity<PagingActivity>() }

    }
}
