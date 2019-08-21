package com.example.mykotlin.view.activity

import android.databinding.DataBindingUtil
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.mykotlin.R
import com.example.mykotlin.databinding.MainBinding
import com.example.mykotlin.base.BaseActivity
import com.example.mykotlin.router.RouterPath
import com.example.mykotlin.viewmodel.WeatherVM

@Route(path = RouterPath.MAIN_ACTIVITY)
class MainActivity : BaseActivity() {
    private lateinit var mainBinding: MainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mainBinding.weatherVM = WeatherVM(this)
    }
}
