package com.example.githubapp.network

import com.example.githubapp.model.UserApi
import com.example.githubapp.utils.Constant

object ApiFactory {

    val userApi : UserApi = RetrofitFactory.retrofit(Constant.BASE_URL)
        .create(UserApi::class.java)
}