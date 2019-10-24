package com.lazy.androidjetpackdemo.Navigation

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
import kotlinx.android.synthetic.main.fragment_login.*

/**
 * 如果用户到达ProfileFragment时未通过身份验证，则他们导航至LoginFragment。
 * 一旦到达那里，他们就可以输入用户名和密码，然后将其传递给LoginViewModel。 '
 * 如果身份验证成功，则ViewModel会将身份验证状态设置为AUTHENTICATED。
 * 这将导致LoginFragment从后堆栈中弹出，从而使用户回到ProfileFragment。
 * 如果由于无效的凭据而导致身份验证失败，则将状态设置为INVALID_AUTHENTICATION，并在LoginFragment中为用户提供一个Snackbar。
 * 最后，如果他们按“后退”按钮，则状态将设置为“未授权”，并且堆栈会弹出回到MainFragment。
 */
class LoginFragment : Fragment() {
    private val viewModel: LoginViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //用户登录
        bt_login.setOnClickListener {
            viewModel.authenticate("mao","123")
        }
        //用户注册
        bt_registered.setOnClickListener { findNavController().navigate(R.id.action_loginFragment_to_navigation4) }


        val navController = findNavController()
         //观察LiveData，并以LifecycleOwner和观察者的身份传入此活动。
        viewModel.authenticationState.observe(viewLifecycleOwner, Observer { authenticationState->
            when(authenticationState){
                LoginViewModel.AuthenticationState.AUTHENTICATED -> navController.popBackStack()
                LoginViewModel.AuthenticationState.INVALID_AUTHENTICATION -> showErrorMessage()
            }
        })

        //如果他们按“后退”按钮，则状态将设置为“未授权”，并且堆栈会弹出回到MainFragment。
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            viewModel.refuseAuthentication()
            navController.popBackStack(R.id.mainFragment, false)
        }
    }
    private fun showErrorMessage() {
    }
}
