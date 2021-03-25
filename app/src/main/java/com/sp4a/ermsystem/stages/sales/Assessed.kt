package com.sp4a.ermsystem.stages.sales

import android.util.Log

class Assessed: SalesState() {

    public override fun Purchase() {
        Log.e("Assessed","we can Purchase now!")
    }

    public override fun Generate() {
        Log.e("Assessed","we can Generate now!")
    }
}