package com.otakmager.fakeposts.detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.otakmager.core.domain.model.Posts
import com.otakmager.fakeposts.R
import com.otakmager.fakeposts.databinding.ActivityDetailPostsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailPostsActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    private lateinit var binding: ActivityDetailPostsBinding

    private val detailPostsViewModel: DetailPostsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailPostsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val detailPosts = intent.getParcelableExtra<Posts>(EXTRA_DATA)
        showDetailPosts(detailPosts)
    }

    private fun showDetailPosts(detailPosts: Posts?) {
        detailPosts?.let {
            supportActionBar?.title = applicationContext.getString(R.string.detail_page)
            binding.tvItemTitle.text = detailPosts.title
            binding.tvItemBody.text = detailPosts.body

            var statusFavorite = detailPosts.isFavorite
            setStatusFavorite(statusFavorite)
            binding.fab.setOnClickListener {
                statusFavorite = !statusFavorite
                detailPostsViewModel.setFavoritePosts(detailPosts, statusFavorite)
                setStatusFavorite(statusFavorite)
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fab.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_favorite_red
                )
            )
        } else {
            binding.fab.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_favorite_not
                )
            )
        }
    }
}