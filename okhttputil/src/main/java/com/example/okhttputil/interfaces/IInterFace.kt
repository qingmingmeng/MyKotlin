package com.example.okhttputil.interfaces

/**
 * Created by zhangjie on 2019/8/1 14:50
 * Description:
 * Passed parameters:
 * Warning:
 */
class IInterFace {
    interface NetCallBack { //网络请求接口返回
        fun onSuccess(flag: String, response: String)
        fun onError(flag: String, e: String)
    }
}