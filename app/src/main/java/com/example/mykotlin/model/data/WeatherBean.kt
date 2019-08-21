package com.example.mykotlin.model.data

/**
 * Created by zhangjie on 2019/8/15 14:29
 * Description:
 * Passed parameters:
 * Warning:
 */
data class WeatherBean(
    val weatherinfo: Weatherinfo
)

data class Weatherinfo(
    val WD: String,
    val WS: String,
    val city: String,
    val njd: String
)