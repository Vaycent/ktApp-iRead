package com.sp4a.ermsystem.stages.sales

import android.util.Log

class Purchased: SalesState() {

    public override fun Generate() {
        Log.e("Purchased","we can Generate now!")
    }
}