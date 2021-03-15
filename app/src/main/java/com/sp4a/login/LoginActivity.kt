package com.sp4a.login

import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.text.TextUtils
import com.google.gson.Gson
import com.sp4a.R
import com.sp4a.login.event.LoginSuccessEvent
import com.sp4a.login.model.bean.User
import com.sp4a.login.model.postLogin
import com.sp4a.manager.BaseActivity
import com.sp4a.service.subscriber.DefaultHttpResultSubscriber
import com.sp4a.util.T
import kotlinx.android.synthetic.main.activity_login.*
import org.greenrobot.eventbus.EventBus
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setLeftTitleAndDisplayHomeAsUp("登录")

        btn_goto_login.setOnClickListener {
            if (TextUtils.isEmpty(et_username.text)) {
                T.showShort(this@LoginActivity, "请输入用户名")
                return@setOnClickListener
            }

            if (TextUtils.isEmpty(et_password.text)) {
                T.showShort(this@LoginActivity, "请输入密码")
                return@setOnClickListener
            }

            postLogin(et_username.text.toString(), et_password.text.toString())
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(object : DefaultHttpResultSubscriber<User>() {
                        override fun onSuccess(data: User?) {
                            val defaultSP = PreferenceManager.getDefaultSharedPreferences(this@LoginActivity)
                            defaultSP.edit().putString("loginUser", Gson().toJson(data)).apply()
                            T.showShort(this@LoginActivity, "登录成功")
                            finish()
                            EventBus.getDefault().post(LoginSuccessEvent())
                        }

                        override fun _onError(status: Int, msg: String?) {
                            T.showShort(this@LoginActivity, msg)
                        }
                    })
        }

        tv_goto_reg.setOnClickListener { startActivity(Intent(this@LoginActivity, RegActivity::class.java)) }
    }
}