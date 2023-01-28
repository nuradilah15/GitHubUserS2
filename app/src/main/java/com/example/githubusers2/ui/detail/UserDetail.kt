package com.example.githubusers2.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.githubusers2.databinding.ActivityUserDetail2Binding

class UserDetail : AppCompatActivity() {

    private lateinit var binding: ActivityUserDetail2Binding
    private lateinit var model: DetailModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDetail2Binding.inflate(layoutInflater)
        setContentView(binding.root)


        val username = intent.getStringExtra(EXTRA_USERNAME)

        val bundle = Bundle()
        bundle.putString(EXTRA_USERNAME, username)

        showLoading(true)
        model = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(DetailModel::class.java)

        if (username != null) {
            model.setDetail(username)
        }
        model.getDetail().observe(this, {
            if (it != null) {
                binding.apply {
                    tvUsernameDetail.text = it.login
                    tvNameDetail.text = it.name
                    tvCompany.text = it.company
                    tvLocation.text = it.location
                    tvRepos.text = "${it.public_repos}"
                    tvFolls.text = "${it.followers}"
                    tvMutual.text = "${it.following}"
                    Glide.with(this@UserDetail)
                        .load(it.avatar_url)
                        .centerCrop()
                        .into(tvIcon)
                    showLoading(false)
                }
            }
        })


        val sectionAdapter = SectionAdapter(this, supportFragmentManager, bundle)
        binding.apply {
            viewPager.adapter = sectionAdapter
            tabLayout.setupWithViewPager(viewPager)
        }
    }


    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressbar.visibility = View.VISIBLE
        } else {
            binding.progressbar.visibility = View.GONE
        }

    }


    companion object {
        const val EXTRA_USERNAME = "extra_username"
    }
}