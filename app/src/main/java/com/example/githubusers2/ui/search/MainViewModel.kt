package com.example.githubusers2.ui.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubusers2.api.RetrofitClient
import com.example.githubusers2.model.User
import com.example.githubusers2.model.UserResponces
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    val userList = MutableLiveData<ArrayList<User>>()

    fun setSearchData(query : String){

        RetrofitClient.apiInstance
            .getSearchUser(query)
            .enqueue(object : Callback<UserResponces>{
                override fun onResponse(
                    call: Call<UserResponces>,
                    response: Response<UserResponces>
                ) {
                    if (response.isSuccessful){
                        userList.postValue(response.body()?.items)
                    }
                }

                override fun onFailure(call: Call<UserResponces>, t: Throwable) {
                    Log.e(TAG, "onFailure: ${t.message}")

                }

            })
    }

    fun getSearch():LiveData<ArrayList<User>>{
        return userList
    }

    companion object{
        const val TAG = "SearchData"
    }
}