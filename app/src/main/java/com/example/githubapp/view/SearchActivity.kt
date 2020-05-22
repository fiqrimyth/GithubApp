package com.example.githubapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubapp.R
import com.example.githubapp.model.AllUser
import com.example.githubapp.utils.AppUtils
import com.example.githubapp.view.adapter.SearchAdapter
import kotlinx.android.synthetic.main.activity_search.*
import java.lang.Exception

class SearchActivity : AppCompatActivity() {

    private val viewModel = SearchVm()
    private val dataUser: MutableList<AllUser> = ArrayList()
    private lateinit var adapterSearch: SearchAdapter
    private var page = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        try {
            if (AppUtils.isNetworkPresent(this)) {
                getData()
            } else {
                AppUtils.defaultDialog(
                    this,
                    this.getString(R.string.no_internet_connection)
                ) {}
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun getData() {
        tvSearch.addTextChangedListener(textWatcher)

        adapterSearch = SearchAdapter(dataUser)
        rvSearch.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = adapterSearch
        }
        rvSearch.setHasFixedSize(true)

        rvSearch.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                adapterSearch.notifyItemInserted(dataUser.size - 1)
                page++

            }
        })
    }

    private val textWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            if (tvSearch.text.toString().trim().isNotEmpty()) {
                viewModel.getAllUser(s.toString())
            }
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

    }

}
