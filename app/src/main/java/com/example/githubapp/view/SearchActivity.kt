package com.example.githubapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubapp.R
import com.example.githubapp.model.Item
import com.example.githubapp.utils.AppUtils
import com.example.githubapp.view.adapter.SearchAdapter
import kotlinx.android.synthetic.main.activity_search.*
import java.lang.Exception

class SearchActivity : AppCompatActivity() {

    private val viewModel = SearchVm()
    private var dataUser: ArrayList<Item> = ArrayList()
    private lateinit var adapterSearch: SearchAdapter
    private lateinit var lManager: LinearLayoutManager
    private var sort = 10
    private var order = 10

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
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun getData() {
        tvSearch.addTextChangedListener(textWatcher)

        lManager = LinearLayoutManager(this@SearchActivity, LinearLayoutManager.VERTICAL, false)
        adapterSearch = SearchAdapter(this)

        rvSearch.layoutManager = lManager
        rvSearch.adapter = adapterSearch
        rvSearch.setHasFixedSize(true)

        rvSearch.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val countItem = lManager.itemCount
                val lastVisibleItemPosition = lManager.findLastVisibleItemPosition()
                val firstVisibleItemPosition = lManager.findFirstVisibleItemPosition()
                val lastPosition = countItem.minus(1) == lastVisibleItemPosition
                val firstPosition = countItem.compareTo(1) == firstVisibleItemPosition

                if ((!firstPosition) && (lastPosition)) {
                    val texts = tvSearch.text.toString()
                    val nextSort = sort + 10
                    val nextOrder = order + 10
                    sort = nextSort
                    order = nextOrder
                    viewModel.getAllUser(texts, "$nextSort", "$nextOrder")
                }
            }
        })

        viewModel.listUser.observe(this, Observer { AllUser ->
            dataUser = AllUser.items as ArrayList
            adapterSearch.putItems(dataUser)
        })

    }

    private val textWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            if (tvSearch.text.toString().trim().isNotEmpty()) {
                viewModel.getAllUser(s.toString(), sort.toString(), order.toString())
            }
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            adapterSearch.clear()
        }

    }
}
