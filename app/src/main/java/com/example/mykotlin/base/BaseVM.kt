package com.example.mykotlin.base

import android.content.Context
import com.example.mykotlin.utils.CommonUtils
import com.example.okhttputil.dialog.Loading

/**
 * Created by zhangjie on 2019/8/19 15:28
 * Description:
 * Passed parameters:
 * Warning:
 */
open class BaseVM(private val context: Context) : BaseNetCallBack {

    fun showLoading(isShow: Boolean){
        Loading.show(context,isShow)
    }

    override fun success(flag: String, resp: Any) {
    }

    override fun error(flag: String, error: String) {
        showLoading(false)
        CommonUtils.showNomalDialog(context,error)
    }
}