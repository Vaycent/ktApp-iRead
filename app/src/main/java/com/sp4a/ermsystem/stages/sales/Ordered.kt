package com.sp4a.ermsystem.stages.sales

import android.util.Log
import java.util.*
import kotlin.properties.Delegates

class Ordered( var orderedId: Long, private val orderedTimeStamp: Date,
              private val orderedOperatorId: Long, private val orderedReceivedTimeStamp: Date,
              private val orderedState: String) : SalesState() {

    /*
    已下单id
    已下单时间
    已下单人id
    已下单接收时间
    已下单状态
     */

//    var orderedId by Delegates.notNull<Long>()
//    lateinit var orderedTimeStamp: Date
//    var orderedOperatorId by Delegates.notNull<Long>()
//    lateinit var orderedReceivedTimeStamp: Date
//    lateinit var orderedState: String

    init {
//        this.orderedId = orderedId
//        this.orderedTimeStamp = orderedTimeStamp
//        this.orderedOperatorId = orderedOperatorId
//        this.orderedReceivedTimeStamp = orderedReceivedTimeStamp
//        this.orderedState = orderedState
        Log.e("Orderd","we main constructor init")
//        Log.e("Orderd","we main orderedId:"+orderedId)
//        Log.e("Orderd","we main orderedTimeStamp:"+orderedTimeStamp)
//        Log.e("Orderd","we main orderedOperatorId:"+orderedOperatorId)
//        Log.e("Orderd","we main orderedReceivedTimeStamp:"+orderedReceivedTimeStamp)
//        Log.e("Orderd","we main orderedState:"+orderedState)


    }

    public final override fun Assess(){
        Log.e("Orderd","we can Assess now!")
    }

//    fun getOrderedId(): Long {
//        return orderedId
//    }
//    fun getOrderedTimeStamp(): Date {
//        return orderedTimeStamp
//    }
//    fun getRrderedOperatorId():Long {
//        return orderedOperatorId
//    }
//    fun getOrderedReceivedTimeStamp(): Date {
//        return orderedReceivedTimeStamp
//    }
//    fun getOrderedState(): String {
//        return orderedState
//    }
}