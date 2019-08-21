package com.example.mykotlin.router

/**
 * Created by zhangjie on 2019/8/21 11:16
 * Description:路由地址管理类，注意：不同的module下，一级路径必须不同，即其他module下路径不能是/app/xxx
 * Passed parameters:
 * Warning:
 */
class RouterPath {
    companion object{
        //activity
        const val MAIN_ACTIVITY = "/app/MainActivity"
        const val SECOND_ACTIVITY = "/app/SecondActivity"
        const val INTERCEPTED_ACTIVITY = "/app/InterceptedActivity"
        //fragment
        const val MY_FRAGMENT = "/app/MyFragment"
        //others
        const val MY_DEGRADE_SERVICE = "/app/MyDegradeService"
    }
}