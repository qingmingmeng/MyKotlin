package com.example.mykotlin.utils

/**
 * Created by zhangjie on 2019/8/20 10:39
 * Description:
 * Passed parameters:
 * Warning:
 */
class CheckUtil {
    companion object{
        //判空
        fun isNullOrEmpty(string: String?): Boolean{
            return (string ?: "").trim().isEmpty()
        }
    }
}