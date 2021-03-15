package com.sp4a

import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex
import com.sp4a.manager.timber.CrashReportingTree
import com.sp4a.service.ServiceFactory
import com.tencent.bugly.Bugly
import net.danlew.android.joda.JodaTimeAndroid
import timber.log.Timber
import timber.log.Timber.DebugTree
import java.lang.ref.WeakReference


/**
 * ClassName:   App
 *
 * Author:      leeeyou
 * Date:        2018/2/24 14:27
 */
class KotlinApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initJoda()
        initBugly()
        initTimber()
        initServiceFactory()
    }

    private fun initServiceFactory() {
        ServiceFactory.DEFAULT_CONTEXT = WeakReference(this)
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        } else {
            Timber.plant(CrashReportingTree())
        }
    }

    private fun initBugly() {
        Bugly.init(this, "8f4e37e626", false)
    }

    private fun initJoda() {
        JodaTimeAndroid.init(this)
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(base)
    }


}