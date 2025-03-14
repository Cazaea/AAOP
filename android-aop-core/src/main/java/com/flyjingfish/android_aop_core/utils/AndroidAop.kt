package com.flyjingfish.android_aop_core.utils

import com.flyjingfish.android_aop_core.listeners.OnCustomInterceptListener
import com.flyjingfish.android_aop_core.listeners.OnPermissionsInterceptListener
import com.flyjingfish.android_aop_core.listeners.OnThrowableListener
import com.flyjingfish.android_aop_core.annotations.TryCatch
import com.flyjingfish.android_aop_core.annotations.CustomIntercept
import com.flyjingfish.android_aop_core.annotations.Permission
import com.flyjingfish.android_aop_core.annotations.Scheduled

object AndroidAop {
    private var onThrowableListener: OnThrowableListener? = null
    private var onCustomInterceptListener: OnCustomInterceptListener? = null
    private var onPermissionsInterceptListener: OnPermissionsInterceptListener? = null

    /**
     * 如果你使用了 [TryCatch], 设置此项可以拿到异常回调
     */
    fun setOnThrowableListener(listener: OnThrowableListener) {
        onThrowableListener = listener
    }

    fun getOnThrowableListener(): OnThrowableListener? {
        return onThrowableListener
    }

    /**
     * 如果你使用了 [CustomIntercept] ,设置此项可以拿到自定义拦截回调
     */
    fun setOnCustomInterceptListener(listener: OnCustomInterceptListener) {
        onCustomInterceptListener = listener
    }

    fun getOnCustomInterceptListener(): OnCustomInterceptListener? {
        return onCustomInterceptListener
    }

    /**
     * 如果你使用了 [Permission], 设置此项才可以请求权限
     */
    fun setOnPermissionsInterceptListener(listener: OnPermissionsInterceptListener) {
        onPermissionsInterceptListener = listener
    }

    fun getOnPermissionsInterceptListener(): OnPermissionsInterceptListener? {
        return onPermissionsInterceptListener
    }

    /**
     * 与 [Scheduled.id] 配合使用，用于停止任务
     */
    fun shutdownNow(key:String){
        AppExecutors.scheduledExecutorMap()[key]?.shutdownNow()
        AppExecutors.scheduledHandlerMap()[key]?.removeCallbacksAndMessages(null)
        AppExecutors.scheduledExecutorMap().remove(key)
        AppExecutors.scheduledHandlerMap().remove(key)
    }

    /**
     * 与 [Scheduled.id] 配合使用，用于停止任务
     */
    fun shutdown(key:String){
        AppExecutors.scheduledExecutorMap()[key]?.shutdown()
        AppExecutors.scheduledHandlerMap()[key]?.removeCallbacksAndMessages(null)
        AppExecutors.scheduledExecutorMap().remove(key)
        AppExecutors.scheduledHandlerMap().remove(key)
    }

}