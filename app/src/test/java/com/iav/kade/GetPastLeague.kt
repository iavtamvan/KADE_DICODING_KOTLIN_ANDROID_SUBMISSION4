package com.iav.kade

import com.iav.kade.Rest.ApiRepository
import org.junit.Test
import org.mockito.Mockito

class GetPastLeague {
    @Test
    fun testDoRequest() {
        val apiRepository = Mockito.mock(ApiRepository::class.java)
        val url = "https://www.thesportsdb.com/api/v1/json/1/eventspastleague.php?id=4328"
        apiRepository.doRequest(url)
        Mockito.verify(apiRepository).doRequest(url)
    }
}