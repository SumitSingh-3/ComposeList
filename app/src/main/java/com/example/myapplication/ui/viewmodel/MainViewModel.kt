package com.example.myapplication.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.network.api.ApiConfig
import com.example.myapplication.network.models.DocsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel: ViewModel() {

    private val _docResponse by lazy { MutableLiveData<DocsResponse>() }
    val docResponse: LiveData<DocsResponse> = _docResponse

    init {
        getDocs()
    }

    private fun getDocs(){

        val client = ApiConfig.getApiService().getDocs()

        client.enqueue(object : Callback<DocsResponse>{
            override fun onResponse(call: Call<DocsResponse>, response: Response<DocsResponse>) {
                val responseBody = response.body()
                if (!response.isSuccessful || responseBody == null) {
                    return
                }

                _docResponse.postValue(responseBody)
            }

            override fun onFailure(call: Call<DocsResponse>, t: Throwable) {
                //handle error
            }

        })

    }

}