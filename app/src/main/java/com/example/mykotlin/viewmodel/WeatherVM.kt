package com.example.mykotlin.viewmodel

import android.content.Context
import android.databinding.ObservableField
import android.widget.TextView
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.callback.NavCallback
import com.alibaba.android.arouter.launcher.ARouter
import com.example.mykotlin.base.BaseVM
import com.example.mykotlin.router.RouterPath
import com.example.mykotlin.model.data.WeatherBean
import com.example.mykotlin.model.data.Weatherinfo
import com.example.mykotlin.model.remote.WeatherRemote
import com.example.mykotlin.utils.CommonUtils
import com.example.mykotlin.view.interfaces.IInterFace
import com.example.mykotlin.view.widget.CommonDialog
import com.example.okhttputil.RequestUrl
import java.util.logging.Logger

/**
 * Created by zhangjie on 2019/8/19 13:18
 * Description:天气信息相关逻辑view model，绑定了view(MainActivity、xml)与model(WeatherRemote、WeatherBean)
 *             通过dataBinding，接口返回时直接刷新UI，用户输入参数后点击请求时直接将参数拼接到请求url中
 * Passed parameters:
 * Warning:
 */
class WeatherVM(private val context: Context): BaseVM(context) {

    private val defaultValueBean = WeatherBean(Weatherinfo("","","",""))//赋默认空值，防止在无数据时展示null的情况
    val weatherBean = ObservableField(defaultValueBean)//接口返回的结果bean，与xml中的字段绑定
    val cityName = ObservableField<String>()//入参字段，与xml中的editText双向绑定

    //弹出dialog方法
    fun showDialog(){
        /*//使用lambda表达式实现回调
        CommonDialog.Builder(context).click{ left: TextView, right: TextView, dialog: CommonDialog ->

        }*/
        CommonDialog.Builder(context)
            .setTitle("温馨提示")
            .setContent("是否请求天气信息？继续操作可能会产生相应的流量费用!")
            .setOnClickListener(object : IInterFace.DialogClick{
                override fun onClick(left: TextView, right: TextView, dialog: CommonDialog) {
                    left.text = "取消"
                    left.setOnClickListener {
                        dialog.dismiss()
                    }
                    right.text = "请求"
                    right.setOnClickListener {
                        dialog.dismiss()
                        //网络请求
                        showLoading(true)
                        WeatherRemote(this@WeatherVM).getWeatherDetail(cityName.get())
                    }
                }
            })
            .create()
            .show()
    }

    /**
     * ARouter 跳转方法
     * 注意:!!!!!!!!
     * 1、withString、... 等 不可以与with(bundle)同时使用，否则目标activity只会接收到bundle的值，其余传参会被覆盖掉
     * 2、降级处理也可以自定义全局的处理(见:MyDegradeService)，只是 单独处理的优先级要高于全局处理的优先级
     */
    fun jumpToSecond(){
        ARouter.getInstance()
            .build(RouterPath.SECOND_ACTIVITY)
            .withString("from","MainActivity--WeatherVM")
            .withInt("times", 1)
            .navigation(context, object : NavCallback() {
                override fun onArrival(postcard: Postcard?) {//已经打开了目标activity
                    Logger.getLogger("降级处理").warning("已经打开了 ${RouterPath.SECOND_ACTIVITY}")
                }

                override fun onFound(postcard: Postcard?) {//找到了要打开的activity
                    super.onFound(postcard)
                    Logger.getLogger("降级处理").warning("已经找到了 ${RouterPath.SECOND_ACTIVITY}")
                }

                override fun onLost(postcard: Postcard?) {//找不到目标activity
                    super.onLost(postcard)
                    Logger.getLogger("降级处理").warning("未找到 ${RouterPath.SECOND_ACTIVITY}")
                }

                override fun onInterrupt(postcard: Postcard?) {//被拦截
                    super.onInterrupt(postcard)
                    Logger.getLogger("降级处理").warning("跳转 ${RouterPath.SECOND_ACTIVITY} 被拦截")
                }
            })
    }

    /**
     * 测试跳转拦截
     */
    fun jumpBeIntercepted(){
        ARouter.getInstance()
            .build(RouterPath.INTERCEPTED_ACTIVITY)
            .navigation(context, object : NavCallback() {
                override fun onArrival(postcard: Postcard?) {//已经打开了目标activity
                    Logger.getLogger("降级处理").warning("已经打开了 ${RouterPath.INTERCEPTED_ACTIVITY}")
                }

                override fun onFound(postcard: Postcard?) {//找到了要打开的activity
                    super.onFound(postcard)
                    Logger.getLogger("降级处理").warning("已经找到了 ${RouterPath.INTERCEPTED_ACTIVITY}")
                }

                override fun onLost(postcard: Postcard?) {//找不到目标activity
                    super.onLost(postcard)
                    Logger.getLogger("降级处理").warning("未找到 ${RouterPath.INTERCEPTED_ACTIVITY}")
                }

                override fun onInterrupt(postcard: Postcard?) {//被拦截
                    super.onInterrupt(postcard)
                    Logger.getLogger("降级处理").warning("跳转 ${RouterPath.INTERCEPTED_ACTIVITY} 被拦截")
                }
            })
    }

    /**
     * 测试跳转一个错误路径，全局捕获找不到目标的处理
     */
    fun jumpAnErrorPath(){
        ARouter.getInstance()
            .build("/test/error")
            .navigation()
    }

    //接口请求成功
    override fun success(flag: String, resp: Any) {
        super.success(flag, resp)
        if (RequestUrl.URL_WEATHER == flag){
            //天气信息请求成功
            showLoading(false)
            weatherBean.set(resp as WeatherBean?)
        }
    }
}