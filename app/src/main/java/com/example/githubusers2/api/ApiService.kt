package com.example.githubusers2.api


import com.example.githubusers2.model.DetailUserResponse
import com.example.githubusers2.model.User
import com.example.githubusers2.model.UserResponces
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("search/users")
    @Headers("Authorization: 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 ")
    fun getSearchUser(
        @Query("q") query: String
    ): Call <UserResponces>

    @GET("users/{username}")
    @Headers("Authorization: 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 ")
    fun getDetailUser(
        @Path("username") username: String
    ): Call<DetailUserResponse>

    @GET("users/{username}/followers")
    @Headers("Authorization:  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 ")
    fun getFollowers(
        @Path("username") username: String
    ): Call <ArrayList<User>>

    @GET("users/{username}/following")
    @Headers("Authorization: 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 ")
    fun getFollowing(
        @Path("username") username: String
    ): Call <ArrayList<User>>
}