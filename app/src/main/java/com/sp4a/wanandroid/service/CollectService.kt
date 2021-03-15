package com.sp4a.wanandroid.service

import com.sp4a.service.entity.HttpResultEntity
import com.sp4a.wanandroid.model.bean.CollectList
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import rx.Observable

interface CollectService {

    @GET("lg/collect/list/{index}/json")
    fun getCollectList(@Path("index") index: Int): Observable<HttpResultEntity<CollectList>>

    @POST("lg/collect/{articleId}/json")
    fun collectInsideArticle(@Path("articleId") articleId: Int): Observable<HttpResultEntity<String>>

    @POST("lg/uncollect_originId/{articleId}/json")
    fun unCollectInsideArticle(@Path("articleId") articleId: Int): Observable<HttpResultEntity<String>>
}