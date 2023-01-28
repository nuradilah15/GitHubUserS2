package com.example.githubusers2.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubusers2.api.RetrofitClient
import com.example.githubusers2.model.DetailUserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailModel : ViewModel() {

    val detailUser = MutableLiveData<DetailUserResponse>()

    fun setDetail(username: String) {
        RetrofitClient.apiInstance
            .getDetailUser(username)
            .enqueue(object : Callback<DetailUserResponse> {
                override fun onResponse(
                    call: Call<DetailUserResponse>,
                    response: Response<DetailUserResponse>
                ) {
                    if (response.isSuccessful) {
                        detailUser.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<DetailUserResponse>, t: Throwable) {
                    Log.d("Failure", t.message.toString())
                }


            })

    }

    fun getDetail(): LiveData<DetailUserResponse> {
        return detailUser
    }
}