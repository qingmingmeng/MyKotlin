package com.example.mykotlin.model.remote

import com.example.mykotlin.base.BaseNetCallBack
import com.example.mykotlin.model.data.WeatherBean
import com.example.mykotlin.utils.CheckUtil
import com.example.okhttputil.HttpDao
import com.example.okhttputil.RequestUrl
import com.example.okhttputil.interfaces.IInterFace
import com.example.okhttputil.utils.JsonUtils

/**
 * Created by zhangjie on 2019/8/19 15:21
 * Description:请求天气信息model，接口调用与回调处理
 * Passed parameters:
 * Warning:
 */
class WeatherRemote(private val callBack: BaseNetCallBack) {
    //请求天气信息
    fun getWeatherDetail(cityName: String?){
        val params = HashMap<String,String>()
        if (!CheckUtil.isNullOrEmpty(cityName)) {
            params["cityName"] = cityName?:""
        }
        HttpDao.get(RequestUrl.URL_WEATHER, params, object : IInterFace.NetCallBack{
            override fun onSuccess(flag: String, response: String) {
                callBack.success(flag, JsonUtils.json2Class(response, WeatherBean::class.java))
            }

            override fun onError(flag: String, e: String) {
                callBack.error(flag, e)
            }
        })
    }
}