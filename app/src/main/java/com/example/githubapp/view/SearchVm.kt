package com.example.githubapp.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubapp.model.AllUser
import com.example.githubapp.network.ApiFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SearchVm : ViewModel() {
    private val userApi = ApiFactory.userApi
    var listUser = MutableLiveData<AllUser>()

    fun getAllUser(searchName: String) {
        GlobalScope.launch(Dispatchers.Main) {
            val getUserName = userApi.getAllUserAsync(searchName, "100", "1000")
            try {
                val response = getUserName.await()
                if (response.isSuccessful) {
                    listUser.value = response.body()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}