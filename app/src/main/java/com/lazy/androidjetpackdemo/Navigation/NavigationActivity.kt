package com.lazy.androidjetpackdemo.Navigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lazy.androidjetpackdemo.R
import kotlinx.android.synthetic.main.fragment_main.*

class NavigationActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)
    }
}