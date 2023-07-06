package com.example.warrenlogin.other

import android.view.View
import androidx.core.view.isVisible

fun View.visible(){
    if (!isVisible) visibility = View.VISIBLE
}

fun View.gone(){
    if(isVisible) visibility = View.GONE
}