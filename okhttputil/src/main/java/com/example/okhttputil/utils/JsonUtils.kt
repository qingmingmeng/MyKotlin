package com.example.okhttputil.utils

import com.alibaba.fastjson.JSON

/**
 * Created by zhangjie on 2019/8/15 13:13
 * Description:
 * Passed parameters:
 * Warning:
 */
class JsonUtils {
    companion object{
        //类转json
        fun <T> class2Json(cls: T): String{
            return JSON.toJSON(cls).toString()
        }

        //json转类
        fun <T> json2Class(json: String, cls: Class<T>): T{
            return JSON.parseObject(json,cls)
        }

        //json转list
        fun <T> json2List(json: String, cls: Class<T>): List<T>{
            return JSON.parseArray(json,cls)
        }
    }
}