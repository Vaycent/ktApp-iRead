package com.sp4a.util

import android.content.Context
import android.content.Intent
import com.sp4a.manager.sonic.BrowserActivity
import com.sp4a.manager.sonic.BrowserActivity.*
import com.sp4a.manager.sonic.MODE_SONIC
import com.sp4a.manager.sonic.SonicJavaScriptInterface

fun startBrowserActivity(context: Context, url: String, title: String) {
    gotoBrowserActivity(context, url, title)
}

private fun gotoBrowserActivity(context: Context, url: String, title: String) {
    val intent = Intent(context, BrowserActivity::class.java)
    intent.putExtra(PARAM_URL, url)
    intent.putExtra(PARAM_MODE, MODE_SONIC)
    intent.putExtra(PARAM_TITLE, title)
    intent.putExtra(SonicJavaScriptInterface.PARAM_CLICK_TIME, System.currentTimeMillis())
    context.startActivity(intent)
}