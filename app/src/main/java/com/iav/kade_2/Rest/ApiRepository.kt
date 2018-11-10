package com.iav.kade_2.Rest

import java.net.URL

class ApiRepository{
    fun doRequest(url: String): String {
        return URL(url).readText()
    }
}