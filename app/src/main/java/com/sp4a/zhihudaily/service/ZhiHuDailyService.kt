package com.sp4a.zhihudaily.service

import com.sp4a.zhihudaily.model.bean.ZhiHuDaily
import com.sp4a.zhihudaily.model.bean.ZhiHuDailyDetail
import retrofit2.http.GET
import retrofit2.http.Path
import rx.Observable

/**
 * ClassName:   ZhiHuRiBaoService
 *
 * Author:      leeeyou
 * Date:        2018/4/24 15:14
 */
interface ZhiHuDailyService {
    @GET("/api/4/news/before/{dateTime}")
    fun getLatestZhiHuDaily(@Path("dateTime") dateTime: String): Observable<ZhiHuDaily>

    @GET("/api/4/news/{id}")
    fun getZhiHuDailyDetailById(@Path("id") id: Int): Observable<ZhiHuDailyDetail>
}