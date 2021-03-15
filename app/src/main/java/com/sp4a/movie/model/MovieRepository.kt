package com.sp4a.movie.model

import com.sp4a.movie.model.bean.MovieDetail
import com.sp4a.movie.model.bean.ResponseHotMovie
import com.sp4a.movie.service.MovieService
import com.sp4a.service.ServiceFactory
import rx.Observable

private const val endPoint = "https://api.douban.com/"

fun fetchHotMovieList(): Observable<ResponseHotMovie> {
    return ServiceFactory
            .createRxRetrofitService(MovieService::class.java, endPoint)
            .getHotMovieList()
}

fun fetchMovieDetail(movieId: String): Observable<MovieDetail> {
    return ServiceFactory.createRxRetrofitService(MovieService::class.java, endPoint)
            .fetchMovieDetail(movieId)
}