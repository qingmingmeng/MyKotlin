package com.example.mykotlin.base

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
import kotlin.properties.Delegates

/**
 * Created by zhangjie on 2019/7/31 19:32
 * Description:
 * Passed parameters:
 * Warning:
 */
class AppApplication: Application() {

    companion object{
        var context: AppApplication by Delegates.notNull()
    }

    override fun onCreate() {
        super.onCreate()
        context = this

        ARouter.openDebug()//调试模式
        ARouter.openLog()//日志输出
        ARouter.printStackTrace()//打印堆栈信息
        ARouter.init(this)//初始化
    }
}