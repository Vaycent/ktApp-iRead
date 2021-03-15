package com.sp4a.service.entity

class HttpResultEntity<T> : BaseEntity<T>() {
    val isSuccess: Boolean
        get() = errorCode == 0
}
