package com.sp4a.ermsystem.stages.sales

import java.lang.Exception

open class SalesState {

    open fun Order(){
        throw Exception("SalesState Order Exception")
    }

    open fun Assess(){
        throw Exception("SalesState Assess Exception")
    }

    open fun Purchase(){
        throw Exception("SalesState Purchase Exception")
    }

    open fun Generate(){
        throw Exception("SalesState Generate Exception")
    }

    open fun Sign(){
        throw Exception("SalesState Sign Exception")
    }
}