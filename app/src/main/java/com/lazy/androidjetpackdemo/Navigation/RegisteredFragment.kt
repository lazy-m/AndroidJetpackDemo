package com.lazy.androidjetpackdemo.Navigation

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.lazy.androidjetpackdemo.Navigation.Model.LoginViewModel

import com.lazy.androidjetpackdemo.R
import kotlinx.android.synthetic.main.fragment_registered.*


class RegisteredFragment : Fragment() {
     val viewModel: LoginViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registered, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bt_registered.setOnClickListener {
            viewModel.authenticate("mao","123")
        }
        findNavController()
        viewModel.authenticationState.observe(viewLifecycleOwner, Observer { authenticationState->
            when(authenticationState){
                LoginViewModel.AuthenticationState.AUTHENTICATED-> findNavController().popBackStack(R.id.profileFragment,false)
                LoginViewModel.AuthenticationState.INVALID_AUTHENTICATION -> findNavController().popBackStack()
            }
        })

        //如果他们按“后退”按钮，则状态将设置为“未授权”，并且堆栈会弹出回到MainFragment。
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            viewModel.refuseAuthentication()
            findNavController().popBackStack()
        }
    }
}
