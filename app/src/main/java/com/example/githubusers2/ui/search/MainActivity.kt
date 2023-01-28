package com.example.githubusers2.ui.search

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubusers2.databinding.ActivityMainBinding
import com.example.githubusers2.model.User
import com.example.githubusers2.ui.detail.UserDetail

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var model : MainViewModel
    private lateinit var adapter : MainUserAdpter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = MainUserAdpter()
        adapter.notifyDataSetChanged()

        adapter.setOnItemClickCallback(object : MainUserAdpter.OnItemClickCallback {

            override fun onItemClicked(user: User) {
                Intent(this@MainActivity, UserDetail::class.java).also {
                    it.putExtra(UserDetail.EXTRA_USERNAME, user.login)
                    startActivity(it)
                }
            }
        })

        model = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get((MainViewModel::class.java))

        binding.apply {

            tvUsers.layoutManager = LinearLayoutManager(this@MainActivity)
            tvUsers.setHasFixedSize(true)
            tvUsers.adapter = adapter



            tvSearch.setOnKeyListener { v, keyCode, event ->
                if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    searchUser()
                    return@setOnKeyListener true
                }

                return@setOnKeyListener false
            }


        }
        model.getSearch().observe(this, {
            if (it != null) {
                adapter.setList(it)
                Loading(false)
            }
        })
    }
    private fun searchUser() {

        binding.apply {
            val query = tvSearch.text.toString()
            if (query.isEmpty()) return
            Loading(true)
            model.setSearchData(query)
        }
    }
    private fun Loading(load: Boolean) {
        if (load) {
            binding.progress.visibility = View.VISIBLE
        } else {
            binding.progress.visibility = View.GONE
        }
    }



}