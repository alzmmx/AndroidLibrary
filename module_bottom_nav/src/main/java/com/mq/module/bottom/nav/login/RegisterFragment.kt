package com.mq.module.bottom.nav.login

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.mq.lib.mvp.BaseMVPFragment
import com.mq.lib.mvp.VBFragment
import com.mq.lib.mvp.presenters
import com.mq.module.bottom.nav.databinding.FragmentRegisterBinding

class RegisterFragment : VBFragment<FragmentRegisterBinding>(), LoginRegisterView {

    private val presenter by presenters<LoginRegisterView, LoginPresenter>(this)
    override fun binding(inflater: LayoutInflater, container: ViewGroup?): FragmentRegisterBinding {
        return FragmentRegisterBinding.inflate(inflater, container, false)
    }

    override fun initView(view: View) {
        binding.back.setOnClickListener {
            presenter.register()
            Navigation.findNavController(view).popBackStack()
        }
    }

    override fun registerSuccess() {
        Toast.makeText(requireContext(), "registerSuccess", Toast.LENGTH_SHORT).show()
    }
}