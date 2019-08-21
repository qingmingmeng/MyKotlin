package com.example.mykotlin.view.activity

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.mykotlin.R
import com.example.mykotlin.base.BaseActivity
import com.example.mykotlin.router.RouterPath
import com.example.mykotlin.view.fragment.MyFragment
import java.util.logging.Logger

/**
 * Created by zhangjie on 2019/8/5 16:38
 * Description:
 * Passed parameters:
 * Warning:
 */
@Route(path = RouterPath.SECOND_ACTIVITY)
class SecondActivity : BaseActivity() {

    @JvmField //kotlin使用ARouter传值时，需要额外增加该注解
    @Autowired(name = "from")//name可填可不填，命名的参数名与上个页面传过来的key值相同时，括号及内容可以省略
    var from: String? = null

    @JvmField
    @Autowired(name = "times")
    var newTimes: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        addFragment(R.id.my_fragment,MyFragment())
        Logger.getLogger("ARouter 传值").warning("from: $from")
        Logger.getLogger("ARouter 传值").warning("times: $newTimes")
    }
}