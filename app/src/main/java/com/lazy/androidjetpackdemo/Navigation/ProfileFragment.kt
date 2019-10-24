package com.lazy.androidjetpackdemo.Navigation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.lazy.androidjetpackdemo.Navigation.Model.LoginViewModel

import com.lazy.androidjetpackdemo.R
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment() {
    private val viewModel: LoginViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.authenticationState.observe(viewLifecycleOwner , Observer {authenticationState->
            when  (authenticationState){
                LoginViewModel.AuthenticationState.AUTHENTICATED ->  showWelcomeMessage()
                LoginViewModel.AuthenticationState.UNAUTHENTICATED -> findNavController().navigate(R.id.loginFragment)
            }
        })
    }
    fun  showWelcomeMessage(){
        tv_status.text="已登录"
    }
}
