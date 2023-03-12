package com.mq.module.bottom.nav.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mq.lib.mvp.BaseMVPFragment
import com.mq.lib.mvp.presenters
import com.mq.module.bottom.nav.databinding.FragmentHomeBinding

class HomeFragment : BaseMVPFragment<FragmentHomeBinding>(), HomeView {

    private val presenter by presenters<HomeView, HomePresenter>(this)

    override fun binding(inflater: LayoutInflater, container: ViewGroup?): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }

    override fun initView(view: View) {
        binding.textHome.setOnClickListener {
            presenter.startLogin(requireContext())
        }
    }
}