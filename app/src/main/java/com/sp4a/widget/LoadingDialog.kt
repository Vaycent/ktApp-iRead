package com.sp4a.widget

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import com.sp4a.R
import kotlinx.android.synthetic.main.loading.*

class LoadingDialog constructor(context: Context, themeResId: Int = R.style.loadingDialog) : Dialog(context, themeResId) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.loading)
        lottieAnimationView.repeatCount = Int.MAX_VALUE
        lottieAnimationView.speed = 1.8F
        lottieAnimationView.playAnimation()
    }

    override fun dismiss() {
        super.dismiss()
        lottieAnimationView.cancelAnimation()
    }

}