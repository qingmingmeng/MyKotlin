package com.example.mykotlin.view.activity

/**
 * Created by zhangjie on 2019/7/30 14:30
 * Description:懒加载单例模式
 * Passed parameters:
 * Warning:
 */
class Singleton private constructor(){
    companion object{
        val instance: Singleton by lazy{
            Singleton()
        }
        var name: String = ""
    }

    fun f1(){}
}