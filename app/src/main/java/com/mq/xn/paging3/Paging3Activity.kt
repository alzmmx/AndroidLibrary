package com.mq.xn.paging3

import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.SimpleItemAnimator
import com.mq.core.BaseViewBindingActivity
import com.mq.xn.databinding.ActivityPaging3Binding
import com.mx.tool.database.CacheEntry
import com.mx.tool.database.DatabaseHelper
import com.mx.tool.ktx.toJson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Paging3Activity : BaseViewBindingActivity<ActivityPaging3Binding>() {
    private val mAdapter = DataPagingAdapter()
    private val viewModel by viewModels<PagingViewModel>()
    override fun binding(): ActivityPaging3Binding {
        return ActivityPaging3Binding.inflate(layoutInflater)
    }

    override fun initView() {
        binding.rvData.adapter = mAdapter
        lifecycleScope.launch {
            viewModel.getData().collect {
                mAdapter.submitData(it)
            }
        }

        binding.insert.setOnClickListener {
            lifecycleScope.launch {
                Log.d("TAG", "----->insert 1")
                val bool = withContext(Dispatchers.IO) {
                    try {
                        DatabaseHelper.instance.getAppDatabase().cacheDao().insert(CacheEntry("id_0", UserData("0", "0-0").toJson()))
                    } catch (e: java.lang.Exception) {
                        e.printStackTrace()
                        Log.d("TAG", "----------->$e")
                    }

                }
                Log.d("TAG", "----------->$bool")
            }
        }


    }
}