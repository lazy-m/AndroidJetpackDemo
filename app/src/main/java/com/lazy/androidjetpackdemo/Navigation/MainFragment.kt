package com.lazy.androidjetpackdemo.Navigation

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController

import com.lazy.androidjetpackdemo.R
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bt_butt.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_mainFragment_to_profileFragment)
        }
        by.setOnClickListener { findNavController().navigate(R.id.pagingWithNetWorkActivity) }
    }
}
