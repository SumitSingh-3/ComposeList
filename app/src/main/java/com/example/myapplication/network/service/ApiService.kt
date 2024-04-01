package com.example.myapplication.network.service

import com.example.myapplication.network.models.DocsResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET(GET_DOC)
    fun getDocs(

    ): Call<DocsResponse>

    companion object {
        const val GET_DOC = "svc/search/v2/articlesearch.json?q=election&api-key=j5GCulxBywG3lX211ZAPkAB8O381S5SM"
    }

}