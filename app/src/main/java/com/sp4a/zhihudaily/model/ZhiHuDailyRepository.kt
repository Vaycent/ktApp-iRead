package com.sp4a.zhihudaily.model

import com.sp4a.service.ServiceFactory
import com.sp4a.zhihudaily.model.bean.ZhiHuDaily
import com.sp4a.zhihudaily.model.bean.ZhiHuDailyDetail
import com.sp4a.zhihudaily.service.ZhiHuDailyService
import rx.Observable

private const val endPoint = "http://news-at.zhihu.com"

fun fetchZhiHuDailyListByDate(date: String): Observable<ZhiHuDaily> {
    return ServiceFactory
            .createRxRetrofitService(ZhiHuDailyService::class.java, endPoint)
            .getLatestZhiHuDaily(date)
}

fun fetchZhiHuDailyDetailById(storyId: Int): Observable<ZhiHuDailyDetail> {
    return ServiceFactory
            .createRxRetrofitService(ZhiHuDailyService::class.java, endPoint)
            .getZhiHuDailyDetailById(storyId)
}