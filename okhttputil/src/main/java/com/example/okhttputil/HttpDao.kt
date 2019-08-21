package com.example.okhttputil

import com.example.okhttputil.interfaces.IInterFace
import com.example.okhttputil.utils.JsonUtils
import kotlinx.coroutines.*
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.internal.wait
import java.io.IOException
import java.net.UnknownHostException
import java.util.concurrent.TimeUnit
import java.util.logging.Logger


/**
 * Created by zhangjie on 2019/8/1 10:34
 * Description:
 * Passed parameters:
 * Warning:
 */
class HttpDao {
    companion object {
        var job: Job? = null
        private var MEDIA_TYPE_PLAIN = "application/json;charset=utf-8".toMediaTypeOrNull()
        private var resp: String = ""
        private var url: String = ""
        private var readTimeout = 30L
        private var writeTimeOut = 30L
        private var connectTimeout = 30L
        /**
         * get请求
         */
        fun get(url: String, params: Map<String, String>? = null, callback: IInterFace.NetCallBack) {
            this.url = url
            val httpClient = OkHttpClient()
            httpClient.newBuilder()
                .readTimeout(readTimeout, TimeUnit.SECONDS)
                .writeTimeout(writeTimeOut, TimeUnit.SECONDS)
                .connectTimeout(connectTimeout, TimeUnit.SECONDS)
            val request = Request
                            .Builder()
                            .url(setGetUrl(url,params))
                            .get()
                            .addHeader("source","demo")
                            .build()
//            clearJob()//清除上次请求产生的协程
//            job = GlobalScope.launch(Dispatchers.IO) {//开启协程，IO线程中请求接口
//                Logger.getLogger("开启协程").warning("IO已开启")
//                try {
//                    resp = httpClient.newCall(request).execute().body?.string()?:""
//                    Logger.getLogger("请求返回").warning(resp)
//                    withContext(Dispatchers.Main){//中止协程，Main线程（UI）线程中进行UI操作
//                        if ("200" in resp){
//                            callback.onSuccess(resp)
//                        } else{
//                            callback.onError(resp)
//                        }
//                    }
//                } catch (e: UnknownHostException){
//                    Logger.getLogger("抛出异常").warning(e.message)
//                    withContext(Dispatchers.Main) {
//                        callback.onError(e.message?:"unknown error")
//                    }
//                }
//            }
            callEnqueue(httpClient.newCall(request), callback)
        }

        /**
         * post请求
         */
        fun <E> post(url: String, params: E, callback: IInterFace.NetCallBack){
            this.url = url
            val httpClient = OkHttpClient()
            val request = Request
                            .Builder()
                            .url(url)
                            .post(JsonUtils.class2Json(params).toRequestBody(MEDIA_TYPE_PLAIN))
                            .addHeader("source","demo")
                            .build()
            callEnqueue(httpClient.newCall(request), callback)
        }

        /**
         * 加入请求队列，结果回调处理
         */
        private fun callEnqueue(call: Call, callback: IInterFace.NetCallBack){
            call.enqueue(object : Callback{
                override fun onResponse(call: Call, response: Response) {
                    resp = response.body?.string()?:""
                    Logger.getLogger("接口返回").warning(resp)
                    GlobalScope.launch(Dispatchers.Main) {
                        callback.onSuccess(url,resp)
                    }
                }

                override fun onFailure(call: Call, e: IOException) {
                    GlobalScope.launch(Dispatchers.Main) {
                        Logger.getLogger("接口返回").warning(e.message?:"")
                        callback.onError(url,e.message?:"")
                    }
                }
            })
        }

        //get请求拼接url
        private fun setGetUrl(url: String, params: Map<String, String>?): String {
            var setGetUrl = url
            if (null != params) {
                for ((k, v) in params) {
                    setGetUrl += if ("?" in setGetUrl) {
                        "&$k=$v"
                    } else {
                        "?$k=$v"
                    }
                }
            }
            Logger.getLogger("请求报文").warning(setGetUrl)
            return setGetUrl
        }

        //关闭协程方法
        fun clearJob(){
            job?.cancel()
            job = null
        }
    }
}