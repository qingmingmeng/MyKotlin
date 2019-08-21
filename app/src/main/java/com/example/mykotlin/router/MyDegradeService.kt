package com.example.mykotlin.router

import android.content.Context
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.facade.service.DegradeService
import com.example.mykotlin.utils.CommonUtils
import java.util.logging.Logger

/**
 * Created by zhangjie on 2019/8/21 15:42
 * Description:全局处理的降级策略
 * Passed parameters:
 * Warning:
 */
@Route(path = RouterPath.MY_DEGRADE_SERVICE)
class MyDegradeService : DegradeService {

    override fun init(context: Context?) {
        Logger.getLogger("ARouter降级策略").warning("降级策略服务初始化")
    }

    override fun onLost(context: Context?, postcard: Postcard?) {//未找到路径时的处理
        //可以自定义处理逻辑或者提示
        //注意:context 有可能为空，当使用路由跳转navigation方法未传context时，这里会为空
        CommonUtils.toast("找不到指定路径:${postcard?.path}")
    }
}