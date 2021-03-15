package com.sp4a.login.model

import com.sp4a.login.model.bean.User
import com.sp4a.movie.service.LoginService
import com.sp4a.service.ServiceFactory
import com.sp4a.service.entity.HttpResultEntity
import rx.Observable

private const val endPoint = "http://www.wanandroid.com/"

fun postRegister(username: String, password: String): Observable<HttpResultEntity<User>> {
    return ServiceFactory
            .createRxRetrofitService(LoginService::class.java, endPoint)
            .register(username, password, password)
}

fun postLogin(username: String, password: String): Observable<HttpResultEntity<User>> {
    return ServiceFactory
            .createRxRetrofitService(LoginService::class.java, endPoint)
            .login(username, password)
}

fun logout(): Observable<HttpResultEntity<String>> {
    return ServiceFactory
            .createRxRetrofitService(LoginService::class.java, endPoint)
            .logout()
}
