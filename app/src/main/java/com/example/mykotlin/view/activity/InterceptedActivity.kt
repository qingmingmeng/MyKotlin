package com.example.mykotlin.view.activity

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.mykotlin.R
import com.example.mykotlin.base.BaseActivity
import com.example.mykotlin.router.RouterPath

/**
 * Created by zhangjie on 2019/8/21 15:53
 * Description:测试ARouter跳转拦截
 * Passed parameters:
 * Warning:
 */
@Route(path = RouterPath.INTERCEPTED_ACTIVITY)
class InterceptedActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intercepted)
    }
}