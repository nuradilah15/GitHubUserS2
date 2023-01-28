package com.example.githubusers2.ui.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubusers2.R
import com.example.githubusers2.databinding.FragmentFollsBinding
import com.example.githubusers2.ui.search.MainUserAdpter

class FollowingFragment : Fragment(R.layout.fragment_folls) {


    private var _binding:FragmentFollsBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: FollowingModel
    private lateinit var adapter: MainUserAdpter
    private lateinit var username: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFollsBinding.bind(view)


        val args = arguments
        username = args?.getString(UserDetail.EXTRA_USERNAME).toString()
        adapter = MainUserAdpter()



        binding.apply {
            rvListView.setHasFixedSize(true)
            rvListView.layoutManager = LinearLayoutManager(activity)
            rvListView.adapter = adapter

        }

        showLoading(true)

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            FollowingModel::class.java
        )
        viewModel.setFollowing(username)
        viewModel.getFollowing().observe(viewLifecycleOwner, {

            if (it != null) {
                adapter.setList(it)
                showLoading(false)
            }


        })
        
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progress.visibility = View.VISIBLE
        } else {
            binding.progress.visibility = View.GONE
        }

    }
}