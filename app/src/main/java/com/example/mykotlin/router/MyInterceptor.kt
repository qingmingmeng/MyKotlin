package com.example.mykotlin.router

import android.content.Context
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.annotation.Interceptor
import com.alibaba.android.arouter.facade.callback.InterceptorCallback
import com.alibaba.android.arouter.facade.template.IInterceptor
import java.lang.RuntimeException
import java.util.logging.Logger

/**
 * Created by zhangjie on 2019/8/21 15:16
 * Description:自定义ARouter拦截器
 * Passed parameters:
 * Warning:
 */
@Interceptor(priority = 1, name = "测试拦截器")//priority 为拦截器的优先级，值越小，优先级越高
class MyInterceptor : IInterceptor {
    override fun init(context: Context?) {
        //初始化拦截器，仅会调用一次
        Logger.getLogger("ARouter拦截器").warning("拦截器初始化")
    }

    /**
     * 拦截器操作
     * @param postcard 跳转数据
     * @param callback 回调
     * 注意:onContinue 与 onInterrupt至少调用其中一种，否则不会继续路由
     */
    override fun process(postcard: Postcard?, callback: InterceptorCallback?) {
        when {
            postcard?.path == RouterPath.SECOND_ACTIVITY -> {//测试用，若为跳转到SecondActivity，修改传参
                postcard.withString("from","来自拦截器修改的数据")
                callback?.onContinue(postcard)//继续跳转
            }

            postcard?.path == RouterPath.INTERCEPTED_ACTIVITY -> //测试用，若为跳转InterceptedActivity，则拦截
                callback?.onInterrupt(null)//终止跳转

            else -> callback?.onContinue(postcard)//继续跳转
        }
//        callback?.onInterrupt(null)//终止跳转
//        callback?.onInterrupt(RuntimeException("手动抛出运行时异常"))//抛出异常
    }
}