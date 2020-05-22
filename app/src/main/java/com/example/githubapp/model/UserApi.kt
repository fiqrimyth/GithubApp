package com.example.githubapp.model

import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApi {

    @GET("/search/users")
    fun getAllUserAsync(
        @Query("q") search: String,
        @Query("sort") sort: String,
        @Query("order") order: String
    ): Deferred<Response<AllUser>>
}