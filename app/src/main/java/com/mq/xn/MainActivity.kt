package com.mq.xn

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mq.core.BaseViewBindingActivity
import com.mq.xn.databinding.ActivityMainBinding
import com.mq.xn.paging3.Paging3Activity
import com.mq.xn.paging3.Repository

class MainActivity : BaseViewBindingActivity<ActivityMainBinding>() {

    override fun binding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun initView() {
        Repository.init()
        binding.button.setOnClickListener {
            startActivity(Intent(this, Paging3Activity::class.java))
        }
    }
}