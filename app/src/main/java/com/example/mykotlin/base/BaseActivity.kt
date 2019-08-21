package com.example.mykotlin.base

import android.app.Activity
import android.os.Bundle
import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.view.View
import com.alibaba.android.arouter.launcher.ARouter
import com.example.okhttputil.HttpDao
import com.example.okhttputil.dialog.Loading

/**
 * Created by zhangjie on 2019/7/30 17:07
 * Description:
 * Passed parameters:
 * Warning:
 */
open class BaseActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ARouter.getInstance().inject(this)//自动注入
    }

    //控件绑定方法
    fun <T : View> Activity.bindView(@IdRes res: Int): Lazy<T>{
        return lazy { findViewById<T>(res) }
    }

    //加载fragment的方法
    //add方法
    fun addFragment(frameId: Int, fragment: Fragment){
        supportFragmentManager.inTransaction { add(frameId,fragment) }
    }
    //replease方法
    fun replaceFragment(frameId: Int, fragment: Fragment){
        supportFragmentManager.inTransaction { replace(frameId,fragment) }
    }
    private inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction){
        beginTransaction().func().commit()
    }

    //destroy时关闭接口调用时建立的协程
    override fun onDestroy() {
        super.onDestroy()
        HttpDao.clearJob()
    }
}