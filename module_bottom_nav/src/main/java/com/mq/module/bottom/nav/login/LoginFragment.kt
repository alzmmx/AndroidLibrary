package com.mq.module.bottom.nav.login

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.mq.lib.mvp.BaseMVPFragment
import com.mq.lib.mvp.presenters
import com.mq.module.bottom.nav.R
import com.mq.module.bottom.nav.databinding.FragmentLoginBinding

class LoginFragment : BaseMVPFragment<FragmentLoginBinding>(), LoginRegisterView {
    private val presenter by presenters<LoginRegisterView, LoginPresenter>(this)
    override fun binding(inflater: LayoutInflater, container: ViewGroup?): FragmentLoginBinding? {
        return FragmentLoginBinding.inflate(inflater, container, false)
    }

    override fun initView(view: View) {
        binding.login.setOnClickListener {
            presenter.login()
            Navigation.findNavController(view).navigate(R.id.action_navigation_login_to_navigation_register)
        }
    }

    override fun loginSuccess() {
        Toast.makeText(requireContext(), "loginSuccess", Toast.LENGTH_SHORT).show()
    }
}