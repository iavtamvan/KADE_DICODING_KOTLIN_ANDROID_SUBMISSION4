package com.example.cia.footballschedule.utils
import android.view.View
import java.text.SimpleDateFormat


fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun simpleDateStringFormat(input:String):String{
    val formatter = SimpleDateFormat("yyyy-MM-dd")
    val date = formatter.parse(input)

    val formatOutput = SimpleDateFormat("EEE, dd MMMM yyy")
    return formatOutput.format(date)
}