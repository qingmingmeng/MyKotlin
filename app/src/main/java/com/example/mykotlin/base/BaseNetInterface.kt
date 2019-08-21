package com.example.mykotlin.base

/**
 * Created by zhangjie on 2019/8/19 15:33
 * Description:
 * Passed parameters:
 * Warning:
 */
open interface BaseNetCallBack {
    fun success(flag: String, resp: Any)
    fun error(flag: String, error: String)
}