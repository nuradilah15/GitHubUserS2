package com.example.githubusers2.ui.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubusers2.R
import com.example.githubusers2.databinding.FragmentFollsBinding
import com.example.githubusers2.ui.search.MainUserAdpter

class FollowsFragment : Fragment(R.layout.fragment_folls) {

    private var binding : FragmentFollsBinding? = null
    private val _binding get() = binding
    private lateinit var model : FollowsModel
    private lateinit var userAdapter: MainUserAdpter
    private lateinit var username : String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFollsBinding.bind(view)
        val arg = arguments
        username = arg?.getString(UserDetail.EXTRA_USERNAME).toString()

        userAdapter = MainUserAdpter()




        _binding?.apply {
            rvListView.setHasFixedSize(true)
            rvListView.layoutManager = LinearLayoutManager(activity)
            rvListView.adapter = userAdapter
        }

        showLoading(true)
        model = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(FollowsModel::class.java)

        model.setFollowers(username)
        model.getFollowers().observe(viewLifecycleOwner,{
            if (it != null){
                userAdapter.setList(it)
                showLoading(false)
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun showLoading(loading: Boolean) {
        if (loading){
            _binding?.progress?.visibility = View.VISIBLE
        }else{
            _binding?.progress?.visibility = View.GONE
        }

    }
}