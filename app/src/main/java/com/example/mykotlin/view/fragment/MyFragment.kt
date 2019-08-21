package com.example.mykotlin.view.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.mykotlin.R
import com.example.mykotlin.router.RouterPath

/**
 * Created by zhangjie on 2019/8/13 16:51
 * Description:
 * Passed parameters:
 * Warning:
 */
@Route(path = RouterPath.MY_FRAGMENT)
class MyFragment : Fragment() {
    var mView: View? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mView = inflater.inflate(R.layout.fragment_my, container, false)
        return mView
    }
}