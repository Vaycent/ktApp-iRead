package com.sp4a

import android.animation.Animator
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.jaeger.library.StatusBarUtil
import com.sp4a.ermsystem.stages.sales.*
import kotlinx.android.synthetic.main.activity_splash.*
import java.util.*

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        StatusBarUtil.setTransparent(this)
        lottieAnimationView.playAnimation()
        lottieAnimationView.speed = 1.5F
        lottieAnimationView.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {
            }

            override fun onAnimationCancel(animation: Animator?) {
            }

            override fun onAnimationStart(animation: Animator?) {
            }

            override fun onAnimationEnd(animation: Animator?) {
                tv_appname.visibility = View.VISIBLE
                startActivity(Intent(this@SplashActivity, IndexActivity::class.java))
                finish()
            }
        })

        NotOrderd().Order()
        var test= Ordered(1, Date(),1001,Date(),"Y")
        test.Assess()
        Log.e("Orderd","we main orderedId:"+test.orderedId)
        Log.e("Orderd","we main orderedTimeStamp:"+test.getOrderedTimeStamp())
        Log.e("Orderd","we main orderedOperatorId:"+test.getRrderedOperatorId())
        Log.e("Orderd","we main orderedReceivedTimeStamp:"+test.getOrderedReceivedTimeStamp())
        Log.e("Orderd","we main orderedState:"+test.getOrderedState())

        Assessed().Generate()
        Assessed().Purchase()
        Purchased().Generate()
        GeneratedContract().Sign()
//        no.Assess()
//        no.Generate()
//        no.Purchase()
    }
}