package com.example.aptivatask.respository


import androidx.lifecycle.MutableLiveData
import com.example.aptivatask.network.ApiService
import com.example.aptivatask.models.ListingItemResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(private val apiService: ApiService){
    fun getListings(
        movieList: MutableLiveData<ListingItemResponse>,
        errorMessage: MutableLiveData<String>
    ) {
        val apiInterface = apiService.getMovies()
        apiInterface.enqueue(object : Callback<ListingItemResponse> {
            override fun onResponse(
                call: Call<ListingItemResponse>,
                response: Response<ListingItemResponse>
            ) {
                if (response.body() != null) {
                    movieList.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<ListingItemResponse>?, t: Throwable?) {
                errorMessage.postValue(t.toString())
            }


        })

    }
}