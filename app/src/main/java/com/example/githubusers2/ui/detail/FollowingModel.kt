package com.example.githubusers2.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubusers2.api.RetrofitClient
import com.example.githubusers2.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowingModel : ViewModel() {

    val following = MutableLiveData<ArrayList<User>>()


    fun setFollowing(username : String){
        RetrofitClient.apiInstance
            .getFollowing(username)
            .enqueue(object : Callback<ArrayList<User>> {
                override fun onResponse(
                    call: Call<ArrayList<User>>,
                    response: Response<ArrayList<User>>
                ) {
                    if (response.isSuccessful){
                        following.postValue(response.body())
                    } else{
                        Log.e(TAG1, "onFailure: ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<ArrayList<User>>, t: Throwable) {
                    Log.e(TAG1, "onFailure ${t.message.toString()}")
                }

            })
    }

    fun getFollowing(): LiveData<ArrayList<User>> {
        return following
    }

    companion object{
        const val TAG1 = "FollowingModel"
    }
}
