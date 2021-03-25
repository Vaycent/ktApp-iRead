package com.sp4a.ermsystem.stages.sales

import android.util.Log

class NotOrderd: SalesState() {

    public override fun Order(){
        Log.e("NotOrderd","we can Order now!")
    }
}