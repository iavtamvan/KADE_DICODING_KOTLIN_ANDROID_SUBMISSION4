package com.iav.kade_2

import com.example.cia.footballschedule.utils.simpleDateStringFormat
import org.junit.Assert
import org.junit.Test

class UtilDateTest {
    @Test
    fun testToDateSimpleString() {
        val dateFormat = simpleDateStringFormat("2018-08-31")
        Assert.assertEquals("Sabtu, 10 November 2018", dateFormat)
    }
}