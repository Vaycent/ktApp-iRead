package com.sp4a.wanandroid.model.bean

data class SystemTagArticleList(val curPage: Int,
                                val datas: List<RecommendItem>,
                                val offset: Int,
                                val over: Boolean,
                                val pageCount: Int,
                                val size: Int,
                                val total: Int)